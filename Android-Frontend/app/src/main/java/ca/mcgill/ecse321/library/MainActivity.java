package ca.mcgill.ecse321.library;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import ca.mcgill.ecse321.library.data.ItemInstance;
import cz.msebera.android.httpclient.Header;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private String error = null;
    private ArrayList<ItemInstance> itemInstances = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_instance);
        init();
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
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
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
                refreshErrorMessage();
            }
        });
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

    public void init() {
        getItemInstances();
        TableLayout layout = findViewById(R.id.itemTable);
        for(int i = 0; i < itemInstances.size(); i++){
            TableRow row= new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);
            TextView id = new TextView(this);
            id.setText(itemInstances.get(i).getSerialNum());
            row.addView(id);
            TextView name = new TextView(this);
            name.setText(itemInstances.get(i).getItemName());
            row.addView(name);
            TextView author = new TextView(this);
            author.setText(itemInstances.get(i).getItemCreator());
            row.addView(author);
            TextView datePublished = new TextView(this);
            datePublished.setText(itemInstances.get(i).getDatePublished());
            row.addView(datePublished);

            layout.addView(row,i);
        }
    }
    public void makeReservation(View view){
        Log.d("jawnie", "boul");
    }


}