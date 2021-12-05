package ca.mcgill.ecse321.library;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.solver.state.State;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.entity.mime.Header;

public class LoginActivity extends MainActivity{
    private String error = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    public void logIn(View v){

        error = "";

        final String username = String.valueOf(findViewById(R.id.login_username));
        final String password = String.valueOf(findViewById(R.id.login_password));


        HttpUtils.post("login/" + username + '/' + password, new RequestParams(), new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if(response.get("password").toString().equalsIgnoreCase(password)){
                        Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
                        intent.putExtra("username", username);
                        startActivity(intent);
                    }else{
                        AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this)
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

    }

    /*private void refreshErrorMessage() {

        // set the error message
        TextView tvError = (TextView) findViewById(R.id.error);
        tvError.setText(error);

        if (error == null || error.length() == 0) {
            tvError.setVisibility(View.GONE);
        } else {
            tvError.setVisibility(View.VISIBLE);
        }
    }*/
}
