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
import android.webkit.CookieManager;
import android.widget.EditText;
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
    private Integer userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx = getApplicationContext();
        itemInstanceErrorView = this.findViewById(R.id.IIerror);
        //getItemInstances();
        setContentView(R.layout.activity_login);

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

        EditText login_username = (EditText)findViewById(R.id.login_username);
        String username = login_username.getText().toString();

        EditText login_password = findViewById(R.id.login_password);
        String password = login_password.getText().toString();


        HttpUtils.put("/login/"+ username + '/' + password, new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, org.json.JSONObject errorResponse) {
                refreshErrorMessage();
                setContentView(R.layout.activity_homepage); //change to homepage

            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, Throwable throwable, org.json.JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
            }
        });
    }

    public void signOut(View v) throws JSONException{
        setContentView(R.layout.activity_login);
    }
    public void libraryHours(View v) throws JSONException{
        setContentView(R.layout.activity_libraryhour);
        getLibraryHours(v);
    }
    public void homepage(View v) throws JSONException{
        setContentView(R.layout.activity_homepage);
    }
    public void goToReservations(View v) throws JSONException{
        setContentView(R.layout.item_instance);
    }

    public void signUp(View v) throws JSONException {
        error = "";

        EditText editText = findViewById(R.id.person_name);
        final String name = editText.getText().toString();

        HttpUtils.post("person/" + name, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                try {
                    int personId = Integer.parseInt(response.get("id").toString());

                    EditText editText1 = findViewById(R.id.street_number);
                    final int streetNumber = Integer.parseInt(editText1.getText().toString());

                    editText1 = findViewById(R.id.street_name);
                    final String streetName = editText1.getText().toString();

                    editText1 = findViewById(R.id.city);
                    final String city = editText1.getText().toString();

                    editText1 = findViewById(R.id.country);
                    final String country = editText1.getText().toString();

                    RequestParams params = new RequestParams();
                    params.add("streetNum", String.valueOf(streetNumber));
                    params.add("streetName", String.valueOf(streetName));
                    params.add("city", String.valueOf(city));
                    params.add("country", String.valueOf(country));

                    HttpUtils.post("address/1", params, new JsonHttpResponseHandler() {

                        @Override
                        public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                            try {
                                int addressId = Integer.parseInt(response.get("id").toString());

                                createCustomer(v, personId, addressId);

                            } catch (Exception e) {

                            }
                        }

                        @Override
                        public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, Throwable throwable, JSONObject errorResponse) {
                            try {
                                error = errorResponse.get("message").toString();
                            } catch (JSONException e) {
                                error = e.getMessage();
                            }
                        }
                    });

                } catch (Exception e) {

                }
            }
            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error = errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error = e.getMessage();
                }
            }
        });
    }

    private void createLibraryCard(View v, Integer customerId) {
        HttpUtils.post("librarycard/" + customerId, new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                createOnlineAccount(v, customerId);
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error = errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error = e.getMessage();
                }
            }
        });
    }

    public void getLibraryHours(View v) {
        HttpUtils.get("libraryhour/find/MONDAY", new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                try {
                    TextView text1=findViewById(R.id.box1);
                    text1.setText(response.get("startTime").toString()+ "AM to " + response.get("endTime").toString() +"PM");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error = errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error = e.getMessage();
                }
            }
        });
        HttpUtils.get("libraryhour/find/TUESDAY", new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                try {
                    TextView text2=findViewById(R.id.box2);
                    text2.setText(response.get("startTime").toString()+ "AM to " + response.get("endTime").toString() +"PM");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error = errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error = e.getMessage();
                }
            }
        });
        HttpUtils.get("libraryhour/find/WEDNESDAY", new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                try {
                    TextView text3=findViewById(R.id.box3);
                    text3.setText(response.get("startTime").toString()+ "AM to " + response.get("endTime").toString() +"PM");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error = errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error = e.getMessage();
                }
            }
        });
        HttpUtils.get("libraryhour/find/THURSDAY", new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                try {
                    TextView text4=findViewById(R.id.box4);
                    text4.setText(response.get("startTime").toString()+ "AM to " + response.get("endTime").toString() +"PM");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error = errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error = e.getMessage();
                }
            }
        });
        HttpUtils.get("libraryhour/find/FRIDAY", new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                try {
                    TextView text5=findViewById(R.id.box5);
                    text5.setText(response.get("startTime").toString()+ "AM to " + response.get("endTime").toString() +"PM");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error = errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error = e.getMessage();
                }
            }
        });
        HttpUtils.get("libraryhour/find/SATURDAY", new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                try {
                    TextView text6=findViewById(R.id.box6);
                    text6.setText(response.get("startTime").toString()+ "AM to " + response.get("endTime").toString() +"PM");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error = errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error = e.getMessage();
                }
            }
        });
        HttpUtils.get("libraryhour/find/SUNDAY", new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                try {
                    TextView text7=findViewById(R.id.box7);
                    text7.setText(response.get("startTime").toString()+ "AM to " + response.get("endTime").toString() +"PM");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error = errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error = e.getMessage();
                }
            }
        });
    }
    private void createCustomer(View v, Integer personId, Integer addressId) {
        HttpUtils.post("customer/1/" + personId + "/0/" + addressId, new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                try {
                    int customerId = Integer.parseInt(response.get("id").toString());

                    createLibraryCard(v, customerId);

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error = errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error = e.getMessage();
                }

                refreshErrorMessage();
            }
        });
    }

    private void createOnlineAccount(View v, Integer customerId) {
        RequestParams params = new RequestParams();
        params.add("personRoleId", String.valueOf(customerId));

        EditText editText = findViewById(R.id.signup_username);
        final String username = editText.getText().toString();

        editText = findViewById(R.id.signup_password);
        final String password = editText.getText().toString();

        editText = findViewById(R.id.email_address);
        final String email = editText.getText().toString();

        HttpUtils.post("onlineaccount/customer/" + username + '/' + password + '/' + email, params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                try {
                    HttpUtils.put("customer/" + customerId + '/' + username, new RequestParams(), new JsonHttpResponseHandler() {

                        @Override
                        public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                            try {
                                login(v,customerId);

                            } catch (Exception e) {

                            }
                        }

                        @Override
                        public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, Throwable throwable, JSONObject errorResponse) {
                            try {
                                error = errorResponse.get("message").toString();
                            } catch (JSONException e) {
                                error = e.getMessage();
                            }

                            refreshErrorMessage();
                        }
                    });

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error = errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error = e.getMessage();
                }

                refreshErrorMessage();
            }
        });
    }


    private void login(View v,Integer customerId){
            EditText editText = findViewById(R.id.signup_username);
            final String username = editText.getText().toString();

            editText = findViewById(R.id.signup_password);
            final String password = editText.getText().toString();

            HttpUtils.put("login/" + username + '/' + password, new RequestParams(), new JsonHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                    userId = customerId;
                    setContentView(R.layout.activity_homepage);
                }

                @Override
                public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    try {
                        error = errorResponse.get("message").toString();
                    } catch (JSONException e) {
                        error = e.getMessage();
                    }

                    refreshErrorMessage();
                }
            });
        }

    public void switchToLogin(View v) {
        setContentView(R.layout.activity_login);
    }

    public void switchToSignup(View v) {
        setContentView(R.layout.activity_signup);
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
                    selectedItemId =  Integer.parseInt(((TextView)((TableRow)v).getChildAt(0)).getText().toString());
                    v.setBackgroundColor(Color.BLACK);
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