package com.example.foodiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodiz.comman.networkchangeListner;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import cz.msebera.android.httpclient.Header;

public class Regstration_page extends AppCompatActivity {

    EditText name,mobileno,emailid,username,password;
    AppCompatButton registerbtn;
    ProgressDialog progressDialog;


    networkchangeListner networkchangeListner = new networkchangeListner();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regstration_page);

        //finding the id of all edit text field

        name=findViewById(R.id.name);
        mobileno=findViewById(R.id.usermobilenumber);
        emailid=findViewById(R.id.useremail);
        username=findViewById(R.id.username);
        password=findViewById(R.id.userregisterpassword);

        registerbtn=findViewById(R.id.registerbtn1);
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().isEmpty()){
                    name.setError("Please enter your Name");
                } else if (mobileno.getText().toString().isEmpty()) {
                    mobileno.setError("Please enter mobile no");
                } else if (emailid.getText().toString().isEmpty()) {
                    emailid.setError("Enter email Id");
                } else if (username.getText().toString().isEmpty()) {
                    username.setError("Enter user name");
                } else if (password.getText().toString().isEmpty()) {
                    password.setError("Enter valid password");
                }else {
                    progressDialog=new ProgressDialog(Regstration_page.this);
                    progressDialog.setTitle("Please Wait...");
                    progressDialog.setMessage("Account is Creating");
                    progressDialog.setCanceledOnTouchOutside(true);
                    progressDialog.show();

                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            "+91" + mobileno.getText().toString(),
                            60, TimeUnit.SECONDS,
                            Regstration_page.this,
                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                    progressDialog.dismiss();
                                    Toast.makeText(Regstration_page.this, "Verification Complete", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {

                                    progressDialog.dismiss();
                                    Toast.makeText(Regstration_page.this, "Verification Failed", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCodeSent(@NonNull String verificationcode,
                                                       @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    super.onCodeSent(verificationcode, forceResendingToken);
                                   Intent intent=new Intent(Regstration_page.this,verfyOTPcode.class);
                                   intent.putExtra("verificationCode",verificationcode);
                                   intent.putExtra("name",name.getText().toString());
                                   intent.putExtra("mobileno",mobileno.getText().toString());
                                   intent.putExtra("emailid",emailid.getText().toString());
                                   intent.putExtra("username",username.getText().toString());
                                   intent.putExtra("password",password.getText().toString());
                                   startActivity(intent);
                                }
                            }
                    );


                    // userRegisterDetail();
                }
            }
        });


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