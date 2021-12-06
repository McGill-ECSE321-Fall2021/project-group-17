package ca.mcgill.ecse321.library;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Gravity;
import android.view.View;

import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import ca.mcgill.ecse321.library.data.ItemInstance;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private static String error = null;
    private static int customerId = 4;
    private static Integer selectedItemId;
    private static String endDate = null;
    private ArrayList<ItemInstance> itemInstances = new ArrayList<>();
    private static Context ctx;
    private static TextView itemInstanceErrorView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_instance);
        ctx = getApplicationContext();
        itemInstanceErrorView = this.findViewById(R.id.IIerror);
        getItemInstances();
        /*
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

    }
    private void refreshErrorMessage() {

        // set the error message
        TextView tvError = (TextView) findViewById(R.id.error);
        tvError.setText(error);

        if (error == null || error.length() == 0) {
            tvError.setVisibility(View.GONE);
        } else {
            tvError.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void logIn(View v){
        error = "";

        final String username = String.valueOf(findViewById(R.id.login_username));
        final String password = String.valueOf(findViewById(R.id.login_password));


        HttpUtils.postByUrl("/login/"+ username+'/'+password, new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if(response.get("password").toString().equalsIgnoreCase(password)){
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);  //loginActivity to homepage
                        intent.putExtra("username", username);
                        startActivity(intent);
                    }else{
                        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                                .setMessage("Wrong password")
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                })
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    error += e.getMessage();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error = errorResponse.getString("message");
                    error = "ERROR: "+ error;
                    itemInstanceErrorView.setTextColor(Color.RED);
                    itemInstanceErrorView.setText(error);
                    itemInstanceErrorView.setVisibility(View.VISIBLE);
                } catch (JSONException e) {
                    error += e.getMessage();
                }
            }
        });

/*
        error = "";

        final TextView username = findViewById(R.id.login_username);
        final TextView password = findViewById(R.id.login_password);


        HttpUtils.post("login/" + username + '/' + password, new RequestParams(), new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                refreshErrorMessage();
                username.setText("");
                password.setText("");
            }

            private void refreshErrorMessage() {
            }

            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
            }
        });
*/
    }

    public void getItemInstances() {
        itemInstances.clear();
        Log.d("Items", "Trying to get item instances");
        HttpUtils.get("iteminstances",new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject obj = response.getJSONObject(i);
                        ItemInstance item = new ItemInstance();
                        item.setSerialNum(obj.getInt("serialNum"));
                        JSONObject checkedItem = obj.getJSONObject("checkableItem");
                        item.setItemName(checkedItem.getString("name"));
                        item.setDatePublished(checkedItem.getString("datePublished"));
                        itemInstances.add(item);
                    }
                }
                catch (JSONException e){
                        e.printStackTrace();
                }
                init();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                catch (NullPointerException e) {
                    System.out.println("Cannot resolve address");
                }
            }
        });
    }

    public void init() {
        //getItemInstances();
        TableLayout layout = findViewById(R.id.itemTable);
        TableRow row = new TableRow(this);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER;

        row.setLayoutParams(lp);

        TextView id = new TextView(this);
        configureTextView(id);
        id.setText("ID");
        row.addView(id);

        TextView name = new TextView(this);
        configureTextView(name);
        name.setText("Name");
        row.addView(name);

        TextView datePublished = new TextView(this);
        configureTextView(datePublished);
        datePublished.setText("Date Published");
        row.addView(datePublished);
        layout.addView(row);

        for(int i = 0; i < itemInstances.size(); i++){
            row= new TableRow(this);
            int color = Color.parseColor("#76323F");
            row.setBackground(new ColorDrawable(color));
            lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);

            row.setLayoutParams(lp);
            id = new TextView(this);
            configureTextView(id);
            id.setText(((Integer)itemInstances.get(i).getSerialNum()).toString());
            row.addView(id);

            name = new TextView(this);
            configureTextView(name);
            name.setText(itemInstances.get(i).getItemName());
            row.addView(name);


            datePublished = new TextView(this);
            configureTextView(datePublished);
            datePublished.setText(itemInstances.get(i).getDatePublished());
            row.addView(datePublished);

            row.setClickable(true);
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id =  Integer.parseInt(((TextView) ((TableRow) v).getChildAt(0)).getText().toString());
                    if (selectedItemId != null && id == selectedItemId) {
                        selectedItemId = null;
                        int color = Color.parseColor("#76323F");
                        v.setBackgroundColor(color);
                    }
                    else{
                        selectedItemId = id;
                        v.setBackgroundColor(Color.BLACK);
                    }

                }
            });

            layout.addView(row,i+1);
        }
    }
    public void makeReservation(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");


    }
    private void configureTextView (TextView tv){

        //tv.setBackground(new ColorDrawable(color));
        tv.setTextSize(20);
        tv.setTextColor(Color.WHITE);
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            endDate = year + "-" + (month+1) + "-" + day;
            JSONObject object = new JSONObject();
            if(selectedItemId == null){
                return;
            }
            try{
                object.accumulate("itemInstanceId",selectedItemId);
                object.accumulate("customerId",((Integer)customerId).toString());
                String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                object.accumulate("dateReserved",date);
                object.accumulate("pickupDay", endDate);
                HttpUtils.postJson(ctx,"reservation/",new StringEntity(object.toString()), new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        itemInstanceErrorView.setTextColor(Color.BLACK);
                        itemInstanceErrorView.setText("Reservation made successfully");
                        itemInstanceErrorView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        Log.d("Bad", ((Integer)statusCode).toString());
                        try {
                            error = errorResponse.getString("message");
                            error = "ERROR: "+ error;
                            itemInstanceErrorView.setTextColor(Color.RED);
                            itemInstanceErrorView.setText(error);
                            itemInstanceErrorView.setVisibility(View.VISIBLE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            catch (JSONException | UnsupportedEncodingException e){
                e.printStackTrace();
            }
        }
    }
}