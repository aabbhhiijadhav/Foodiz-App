package com.example.foodiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.foodiz.comman.Urls;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MyProfileActivity extends AppCompatActivity {

    TextView profile_name,profile_mobileno,profile_emailid,profile_username;
    ImageView profileimage;
    SharedPreferences sharedPreferences;
    String strusername;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        //find the id all presented textview
        profileimage=findViewById(R.id.profilephpto);
        profile_name=findViewById(R.id.profile_name);
        profile_mobileno=findViewById(R.id.profile_mobileno);
        profile_emailid=findViewById(R.id.profile_emailid);
        profile_username=findViewById(R.id.profile_usename);

        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(MyProfileActivity.this);

         strusername=sharedPreferences.getString("username","");


    }
    @Override
    protected void onStart(){
        super.onStart();
       progressDialog=new ProgressDialog(MyProfileActivity.this);
       progressDialog.setTitle("My Profile");
       progressDialog.setMessage("Please wait..");
       progressDialog.setCanceledOnTouchOutside(true);
       progressDialog.show();
       getmydetails();

    }

    private void getmydetails() {
        AsyncHttpClient client=new AsyncHttpClient();
        RequestParams params=new RequestParams();

        params.put("username",strusername);
        client.post(Urls.myDetailsWebService,params,new JsonHttpResponseHandler()
                {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);

                        progressDialog.dismiss();
                        try {
                            JSONArray jsonArray=response.getJSONArray("getmydetail");
                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject=jsonArray.getJSONObject(i);

                                String id=jsonObject.getString("id");
                                String image=jsonObject.getString("image");
                                String name=jsonObject.getString("name");
                                String mobile_no=jsonObject.getString("mobileno");
                                String emailid=jsonObject.getString("emailid");
                                String username=jsonObject.getString("username");


                                profile_name.setText(name);
                                profile_mobileno.setText(mobile_no);
                                profile_emailid.setText(emailid);
                                profile_username.setText(username);



                                //setting image to profile image view
                                Glide.with(MyProfileActivity.this)
                                        .load(Urls.ImageInServer+image)
                                        .skipMemoryCache(true)
                                        .error(R.drawable.imageloading)
                                        .into(profileimage);


                            }



                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                        progressDialog.dismiss();
                        Toast.makeText(MyProfileActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                    }
                }


        );
    }

}