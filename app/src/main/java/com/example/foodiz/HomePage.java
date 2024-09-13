package com.example.foodiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomePage extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    //declare bottom navigation view for use
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //findinjg the id for bottom navigation view
        bottomNavigationView=findViewById(R.id.home_page_bottomnavigationview);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        //this metod is used for when we will open our application that time which is our default bottom navigation menu and our default navigation fragment.
        bottomNavigationView.setSelectedItemId(R.id.navigation_menu_Home);




    }

    //this method is compalsary because when we click on any item of navigation item
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        return false;
    }
}