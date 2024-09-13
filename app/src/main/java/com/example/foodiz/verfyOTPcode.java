package com.example.foodiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodiz.comman.Urls;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import cz.msebera.android.httpclient.Header;

public class verfyOTPcode extends AppCompatActivity {


    private String strverificationcode,strname,strmobilno,stremailid,strusername,strpassword;

    //creating a progress dialog
    ProgressDialog progressDialog;

    TextView mobileno,resent;
    EditText verification_EDT1,verification_EDT2,verification_EDT3,verification_EDT4,verification_EDT5,verification_EDT6;
    AppCompatButton verifyButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verfy_otpcode);

        //finding the id of all objects
        mobileno=findViewById(R.id.verifyotpno);
        resent=findViewById(R.id.verfiy_resentotp);
        verification_EDT1=findViewById(R.id.verification_EDT1);
        verification_EDT2=findViewById(R.id.verification_EDT2);
        verification_EDT3=findViewById(R.id.verification_EDT3);
        verification_EDT4=findViewById(R.id.verification_EDT4);
        verification_EDT5=findViewById(R.id.verification_EDT5);
        verification_EDT6=findViewById(R.id.verification_EDT6);
        verifyButton=findViewById(R.id.verfiy_btn);



        //passing the value to string variable
        strverificationcode=getIntent().getStringExtra("verificationCode");
        strname=getIntent().getStringExtra("name");
        strmobilno=getIntent().getStringExtra("mobileno");
        stremailid=getIntent().getStringExtra("emailid");
        strusername=getIntent().getStringExtra("username");
        strpassword=getIntent().getStringExtra("password");


        mobileno.setText(strmobilno);


        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(verification_EDT1.getText().toString().trim().isEmpty() || verification_EDT2.getText().toString().trim().isEmpty() || verification_EDT3.getText().toString().trim().isEmpty()
                || verification_EDT4.getText().toString().trim().isEmpty() || verification_EDT5.getText().toString().trim().isEmpty() || verification_EDT6.getText().toString().trim().isEmpty()){
                    Toast.makeText(verfyOTPcode.this, "Please Enter valid OTP", Toast.LENGTH_SHORT).show();
                }
                String otpcode=verification_EDT1.getText().toString()+verification_EDT2.getText().toString()+verification_EDT3.getText().toString()+verification_EDT4.getText().toString()+verification_EDT5.getText().toString()+verification_EDT6.getText().toString();

                if (strverificationcode!=null){
                    progressDialog=new ProgressDialog(verfyOTPcode.this);
                    progressDialog.setTitle("Verifying OTP");
                    progressDialog.setMessage("Please Wait....");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                   PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(strverificationcode,otpcode);

                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                progressDialog.dismiss();
                                userRegisterDetail();
                            }else {
                                progressDialog.dismiss();
                                Toast.makeText(verfyOTPcode.this, "Verification Faild", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });



        resent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + strmobilno,
                        60, TimeUnit.SECONDS,
                        verfyOTPcode.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                Toast.makeText(verfyOTPcode.this, "Verification Complete", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {


                                Toast.makeText(verfyOTPcode.this, "Verification Failed", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String newverificationcode,
                                                   @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                               strverificationcode=newverificationcode;
                            }
                        }
                );
            }
        });


        setupInputOTP();






    }

    private void setupInputOTP() {
        verification_EDT1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               if (!charSequence.toString().trim().isEmpty()){
                   verification_EDT2.requestFocus();
               }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        verification_EDT2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().trim().isEmpty()){
                    verification_EDT3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        verification_EDT3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().trim().isEmpty()){
                    verification_EDT4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        verification_EDT4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().trim().isEmpty()){
                    verification_EDT5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        verification_EDT5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().trim().isEmpty()){
                    verification_EDT6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    private void userRegisterDetail() {
        //this class is used for server communication
        AsyncHttpClient client=new AsyncHttpClient();   //client and server communication.
        RequestParams params=new RequestParams();  //put data

        params.put("name",strname);
        params.put("mobileno",strmobilno);
        params.put("emailid",stremailid);
        params.put("username",strusername);
        params.put("password",strpassword);

        client.post(Urls.UserRegisterWebservices
                ,params,new JsonHttpResponseHandler()
                {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        try {
                            String status =response.getString("success");
                            if (status.equals("1")){
                                Toast.makeText(verfyOTPcode.this, "Succesfully insertion", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(verfyOTPcode.this, HomePage.class));
                                progressDialog.dismiss();
                            }else {
                                progressDialog.dismiss();
                                Toast.makeText(verfyOTPcode.this, "Already data present", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }


                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                        progressDialog.dismiss();

                        Toast.makeText(verfyOTPcode.this, "Server error", Toast.LENGTH_SHORT).show();
                    }
                }
        );

    }
}