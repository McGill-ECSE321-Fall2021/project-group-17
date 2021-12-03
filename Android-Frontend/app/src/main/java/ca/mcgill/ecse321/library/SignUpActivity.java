package ca.mcgill.ecse321.library;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.entity.mime.Header;

public class SignUpActivity extends MainActivity {
    private String error = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
    }

    public void signUp(View v) throws JSONException {
        error = "";

        final String name = String.valueOf(findViewById(R.id.person_name));
        final String username = String.valueOf(findViewById(R.id.signup_username));
        final String password = String.valueOf(findViewById(R.id.signup_password));
        final String email = String.valueOf(findViewById(R.id.email_address));
        final String streetNumber = String.valueOf(findViewById(R.id.street_number));
        final String streetName = String.valueOf(findViewById(R.id.street_name));
        final String city = String.valueOf(findViewById(R.id.city));
        final String country = String.valueOf(findViewById(R.id.country));

        JSONObject jsonPerson = new JSONObject();
        jsonPerson.put("name", name);
        jsonPerson.put("personRole", "customer");

        HttpUtils.post("person/" + name, new RequestParams(), new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if (response.get("id").toString() != null) {
                        int personId = Integer.parseInt(response.get("id").toString());


                        HttpUtils.post("address/1/" + streetNumber + '/' + streetName + '/' + city + '/' + country, new RequestParams(), new JsonHttpResponseHandler() {

                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                try {
                                    int addressId = Integer.parseInt(response.get("id").toString());

                                    HttpUtils.post("customer/1/" + personId + "/0/" + addressId, new RequestParams(), new JsonHttpResponseHandler() {

                                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                            try {
                                                int customerId = Integer.parseInt(response.get("id").toString());

                                                HttpUtils.post("librarycard/" + customerId, new RequestParams(), new JsonHttpResponseHandler() {

                                                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                                        try {

                                                            RequestParams params = new RequestParams();
                                                            params.add("personRoleId", String.valueOf(customerId));

                                                            HttpUtils.post("onlineaccount/customer/" + username + '/' + password + '/' + email, params, new JsonHttpResponseHandler() {

                                                                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                                                    try {
                                                                        HttpUtils.post("customer/" + customerId + '/' + username, new RequestParams(), new JsonHttpResponseHandler() {

                                                                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                                                                try {


                                                                                } catch (Exception e) {

                                                                                }
                                                                            }


                                                                            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

                                                                            }
                                                                        });

                                                                    } catch (Exception e) {

                                                                    }
                                                                }


                                                                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

                                                                }
                                                            });

                                                        } catch (Exception e) {

                                                        }
                                                    }


                                                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

                                                    }
                                                });

                                            } catch (Exception e) {

                                            }
                                        }


                                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

                                        }
                                    });
                                } catch (Exception e) {

                                }
                            }


                            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

                            }
                        });

                    }
                } catch (Exception e) {

                }
            }

            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

            }
        });

        HttpUtils.post("login/" + username + '/' + password, new RequestParams(), new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

            }

            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

            }
        });
    }

}
