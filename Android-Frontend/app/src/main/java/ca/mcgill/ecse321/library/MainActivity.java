package ca.mcgill.ecse321.library;

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
                setContentView(R.layout.activity_signup);
                //Intent intent = new Intent(MainActivity.this, FirstFragment.class);  //loginActivity to homepage
                //intent.putExtra("username", username);
                //startActivity(intent);

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