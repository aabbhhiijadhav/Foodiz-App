package com.example.foodiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class splashactivity extends AppCompatActivity {

    ImageView splashimage1;

    Handler handler;
    Animation animation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashactivity);

        splashimage1=findViewById(R.id.splashimage1);

        animation=AnimationUtils.loadAnimation(splashactivity.this,R.anim.animation);
        splashimage1.startAnimation(animation);



        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(splashactivity.this,login_page.class);
                startActivity(intent);
                finish();
            }

        },4000);

    }
}