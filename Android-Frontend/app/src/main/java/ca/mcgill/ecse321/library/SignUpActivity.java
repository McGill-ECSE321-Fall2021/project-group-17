package ca.mcgill.ecse321.library;

import android.os.Bundle;
import android.view.View;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.entity.mime.Header;

public class SignUpActivity extends MainActivity {
    private String error = null;
    private Integer customerId;
    private Integer personId;
    private Integer addressId;
    private final String name = String.valueOf(findViewById(R.id.person_name));
    private final String username = String.valueOf(findViewById(R.id.signup_username));
    private final String password = String.valueOf(findViewById(R.id.signup_password));
    private final String email = String.valueOf(findViewById(R.id.email_address));
    private final String streetNumber = String.valueOf(findViewById(R.id.street_number));
    private final String streetName = String.valueOf(findViewById(R.id.street_name));
    private final String city = String.valueOf(findViewById(R.id.city));
    private final String country = String.valueOf(findViewById(R.id.country));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void signUp(View v) throws JSONException {
        error = "";

        JSONObject jsonPerson = new JSONObject();
        jsonPerson.put("name", name);
        jsonPerson.put("personRole", "customer");

        HttpUtils.post("person/" + name, new RequestParams(), new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if (response.get("id").toString() != null) {
                        personId = Integer.parseInt(response.get("id").toString());

                        RequestParams params = new RequestParams();
                        params.add("streetNum", String.valueOf(streetNumber));
                        params.add("streetName", String.valueOf(streetName));
                        params.add("city", String.valueOf(city));
                        params.add("country", String.valueOf(country));

                        HttpUtils.post("address/1", params, new JsonHttpResponseHandler() {

                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                try {
                                    addressId = Integer.parseInt(response.get("id").toString());

                                    createCustomer(v);

                                } catch (Exception e) {

                                }
                            }

                            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
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

            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error = errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error = e.getMessage();
                }
            }
        });


    }

    private void createLibraryCard(View v) {
        HttpUtils.post("librarycard/" + customerId, new RequestParams(), new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                createOnlineAccount(v);
            }


            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error = errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error = e.getMessage();
                }
            }
        });
    }

    private void createCustomer(View v) {
        HttpUtils.post("customer/1/" + personId + "/0/" + addressId, new RequestParams(), new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    customerId = Integer.parseInt(response.get("id").toString());

                    createLibraryCard(v);

                } catch (Exception e) {

                }
            }


            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error = errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error = e.getMessage();
                }
            }
        });
    }

    private void createOnlineAccount(View v) {
        RequestParams params = new RequestParams();
        params.add("personRoleId", String.valueOf(customerId));

        HttpUtils.post("onlineaccount/customer/" + username + '/' + password + '/' + email, params, new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    HttpUtils.post("customer/" + customerId + '/' + username, new RequestParams(), new JsonHttpResponseHandler() {

                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            try {
                                login(v);

                            } catch (Exception e) {

                            }
                        }

                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
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


            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error = errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error = e.getMessage();
                }
            }
        });
    }

    private void login(View v) {
        HttpUtils.post("login/" + username + '/' + password, new RequestParams(), new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

            }

            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error = errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error = e.getMessage();
                }
            }
        });
    }

}
