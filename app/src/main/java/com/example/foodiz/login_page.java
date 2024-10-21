package com.example.foodiz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodiz.Admin.admin;
import com.example.foodiz.comman.Urls;
import com.example.foodiz.comman.networkchangeListner;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class login_page extends AppCompatActivity {

    AppCompatButton loginbtn1,btnlogin,siginwithgoogle;
    TextView logintext1,logintext2;

    EditText usernameinput,passwordinput;
    networkchangeListner networkchangeListner= new networkchangeListner();

    //declaration of progressdialog
    ProgressDialog progressDialog;



    //This class is used for when we are click on sigin with google when our multiple gmail id is present on google it help for choose the google account
    GoogleSignInOptions googleSignInOptions;
    //this class is used when we select google account it will help to show which account is selected by user
    GoogleSignInClient googleSignInClient;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);



        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(login_page.this);
        editor=sharedPreferences.edit();







        btnlogin=findViewById(R.id.loginbtn1);

        //finding the id of edit text
        usernameinput=findViewById(R.id.usernameinput);
        passwordinput=findViewById(R.id.passwordinput);


        //finding the id of button and used it
        loginbtn1=findViewById(R.id.loginbtn1);
        loginbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(usernameinput.getText().toString().isEmpty()){
                    usernameinput.setError("Please Enter username?");
                } else if (passwordinput.getText().toString().isEmpty()) {
                    passwordinput.setError("please Enter valid Password?");

                }
                else {
                    //if we are creating progress dialog first create a memory allocation in memory
                    progressDialog=new ProgressDialog(login_page.this);
                    progressDialog.setTitle("Please Wait...");
                    progressDialog.setMessage("Loging in process");
                    progressDialog.setCanceledOnTouchOutside(true);
                    progressDialog.show();

                    userLogin();
                }
            }
        });






        //code for sign in with google
        //insilizing the class whic we are create upword
        googleSignInOptions=new GoogleSignInOptions.Builder(googleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleSignInClient= GoogleSignIn.getClient(login_page.this,googleSignInOptions);

        //working of this classes from where it will be fetch the data and where paste it
        //googlesigninoption => googlesigninclient => googlesigin => googlesignin account








        siginwithgoogle=findViewById(R.id.sigininwithgoole);
        siginwithgoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SignIn();
            }
        });



















        //for the regestration page
        logintext2=findViewById(R.id.newaccount);
        logintext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login_page.this, Regstration_page.class));
            }
        });

    }








    //method for login with google
    private void SignIn() {

        Intent intent=googleSignInClient.getSignInIntent();
        //this method is used for store result

        startActivityForResult(intent,999);










    }

    //this method is compulsary when we used startActivityForResult() method.
    //this method is use for show result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==999){

            Task<GoogleSignInAccount> task= GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                task.getResult(ApiException.class);
               Intent intent=new Intent(login_page.this, HomePage.class);
               startActivity(intent);
                finish();
            } catch (ApiException e) {
               Toast.makeText(login_page.this,"Something went Wrong",Toast.LENGTH_SHORT);
            }
        }

    }






    //method for put the data from database and chek the user is avilable in database or not.
    private void userLogin() {

        //this class is used for communication between client and server.send data from applicatin and then accept the answer from server
        AsyncHttpClient client=new AsyncHttpClient();
        //create the class to transfer the data from this method
        RequestParams params=new RequestParams();
        params.put("username",usernameinput.getText().toString());
        params.put("password",passwordinput.getText().toString());


        client.post(Urls.LoginUserWebservice
                ,params,new JsonHttpResponseHandler()
                {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        try {
                            String status =response.getString("success");
                            String user_role = response.getString("user_role");
                            if (status.equals("1")&&user_role.toUpperCase().equals("USER")){
                                Toast.makeText(login_page.this, "Succesfully Login", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(login_page.this, HomePage.class));
                                editor.putString("username",usernameinput.getText().toString()).commit();
                                progressDialog.dismiss();
                            } else if (status.equals("1")&&user_role.toUpperCase().equals("ADMIN")) {
                                Toast.makeText(login_page.this, "Succesfully Login", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(login_page.this, admin.class));
                                progressDialog.dismiss();
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(login_page.this, "Login Failed try again", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }


                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                        progressDialog.dismiss();

                        Toast.makeText(login_page.this, "Server error", Toast.LENGTH_SHORT).show();
                    }
                }
        );

    }






    @Override
    protected void onStart(){
        super.onStart();
        IntentFilter intentFilter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkchangeListner,intentFilter);
    }
    @Override
    protected void onStop(){
        super.onStop();
        unregisterReceiver(networkchangeListner);
    }
}






























