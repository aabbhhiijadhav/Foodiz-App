package com.example.foodiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodiz.comman.Urls;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class updateprofile extends AppCompatActivity {

    //creation of object
    EditText profile_name,profile_mobileno,profile_emailid,profile_username;
    AppCompatButton update_profile;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateprofile);

        //finding the id of object

        profile_name = findViewById(R.id.update_name);
        profile_mobileno = findViewById(R.id.update_mobileno);
        profile_emailid = findViewById(R.id.update_emailid);
        profile_username = findViewById(R.id.update_usename);








        //we are accepting the data from the Myprofile Activity using intent
        String strname,strusermobileno,stremailid,strusername;
        strname = getIntent().getStringExtra("person_name");
        strusermobileno = getIntent().getStringExtra("mobile_no");
        stremailid = getIntent().getStringExtra("profile_email");
        strusername = getIntent().getStringExtra("profle_username");


        profile_name.setText(strname);
        profile_mobileno.setText(strusermobileno);
        profile_emailid.setText(stremailid);
        profile_username.setText(strusername);





        update_profile=findViewById(R.id.updateprofile);
        update_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(updateprofile.this);
                progressDialog.setTitle("Updating Profile");
                progressDialog.setMessage("Please Wait...");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                updateUserProfile();
            }
        });
        
    }

    //method is creating for establish the connection between database or our application
    private void updateUserProfile() {
       //establishing the connection between server side and client side
        AsyncHttpClient client = new AsyncHttpClient();

        //the request params is used to transfered the data from client side to server side
        RequestParams params = new RequestParams();

        params.put("profile_name",profile_name.getText().toString());
        params.put("profile_mobileno",profile_mobileno.getText().toString());
        params.put("profile_emailid",profile_emailid.getText().toString());
        params.put("profile_username",profile_username.getText().toString());


        client.post(Urls.update_profile,
                params,
                new JsonHttpResponseHandler()
                {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        //we are taking here try catch block because here is our runtime program if any error occured her then it turned on next move
                        try {
                            String status = response.getString("sucesses");
                            if (status.equals("1")){
                                progressDialog.dismiss();
                                Toast.makeText(updateprofile.this, "Profile Update Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(updateprofile.this,MyProfileActivity.class));
                            }else {
                                progressDialog.dismiss();
                                Toast.makeText(updateprofile.this, "Not Update", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                        progressDialog.dismiss();
                        Toast.makeText(updateprofile.this, "Server Error", Toast.LENGTH_SHORT).show();

                    }
                }
        );

    }

}