package ca.mcgill.ecse321.library;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import ca.mcgill.ecse321.library.databinding.ActivityMainBinding;
import cz.msebera.android.httpclient.entity.mime.Header;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private String error = null;
    /*private Integer customerId;
    private Integer personId;
    private Integer addressId;
    private final String name = String.valueOf(findViewById(R.id.person_name));
    private final String username = String.valueOf(findViewById(R.id.signup_username));
    private final String password = String.valueOf(findViewById(R.id.signup_password));
    private final String email = String.valueOf(findViewById(R.id.email_address));
    private final String streetNumber = String.valueOf(findViewById(R.id.street_number));
    private final String streetName = String.valueOf(findViewById(R.id.street_name));
    private final String city = String.valueOf(findViewById(R.id.city));
    private final String country = String.valueOf(findViewById(R.id.country));*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
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
    public void signOut(View v) throws JSONException{
        setContentView(R.layout.activity_login);
    }
    public void libraryHours(View v) throws JSONException{
        setContentView(R.layout.activity_libraryhour);
    }
    public void homepage(View v) throws JSONException{
        setContentView(R.layout.activity_homepage);
    }

    public void signUp(View v) throws JSONException {
        error = "";

        EditText editText = findViewById(R.id.person_name);
        final String name = editText.getText().toString();

        //final String name = String.valueOf(findViewById(R.id.person_name));

        HttpUtils.post("person/" + name, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                try {
                    if (response.get("id").toString() != null) {
                        int personId = Integer.parseInt(response.get("id").toString());

                        /*final String streetNumber = String.valueOf(findViewById(R.id.street_number));
                        final String streetName = String.valueOf(findViewById(R.id.street_name));
                        final String city = String.valueOf(findViewById(R.id.city));
                        final String country = String.valueOf(findViewById(R.id.country));*/

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

                    }
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

        //final String username = String.valueOf(findViewById(R.id.signup_username));
        //final String password = String.valueOf(findViewById(R.id.signup_password));
        //final String email = String.valueOf(findViewById(R.id.email_address));

        HttpUtils.post("onlineaccount/customer/" + username + '/' + password + '/' + email, params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                try {
                    HttpUtils.put("customer/" + customerId + '/' + username, new RequestParams(), new JsonHttpResponseHandler() {

                        @Override
                        public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                            try {
                                login(v);

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

    private void login(View v) {
        EditText editText = findViewById(R.id.signup_username);
        final String username = editText.getText().toString();

        editText = findViewById(R.id.signup_password);
        final String password = editText.getText().toString();

        HttpUtils.put("login/" + username + '/' + password, new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {

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

}