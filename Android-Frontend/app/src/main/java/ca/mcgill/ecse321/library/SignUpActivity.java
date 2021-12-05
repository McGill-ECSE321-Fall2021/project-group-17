package ca.mcgill.ecse321.library;

import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.widget.EditText;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUpActivity extends MainActivity {
    private String error = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void signUp(View v) {
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
                refreshErrorMessage();
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
                                login(v, customerId);

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

    private void login(View v, Integer customerId) {
        EditText editText = findViewById(R.id.signup_username);
        final String username = editText.getText().toString();

        editText = findViewById(R.id.signup_password);
        final String password = editText.getText().toString();

        HttpUtils.put("login/" + username + '/' + password, new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                CookieManager cookieManager = CookieManager.getInstance();
                cookieManager.setCookie("http://10.0.2.2:8080/", "customerId=" + customerId + ';');
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
