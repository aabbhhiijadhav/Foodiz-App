package com.example.foodiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

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

        //this method is used for when we will open our application that time which is our default bottom navigation menu and our default navigation fragment.
        bottomNavigationView.setSelectedItemId(R.id.navigation_menu_Home);





    }


    //when we create a fragment in our application before using it first initialize that fragment.
    //initialization of home fragment
    HomeFragment homeFragment = new HomeFragment();

    //initialization of category fragment
    CategoryFragment categoryFragment=new CategoryFragment();

    //initialization of myorder fragment
    MyorderFragment myorderFragment = new MyorderFragment();


    //this method is compalsary because when we click on any item of navigation item
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //decision when we are click on Home button
        if (item.getItemId() == R.id.navigation_menu_Home){
            //this process is used for load the fragment on the our framnr layout

            //use of all methods
            //getSupportFragementManager = this method is used to load the fragment
            //beginTransaction() = this method is used for top start the load the fragment in the framelayout
            //replace = replace method is used to replace a new fragment on the place present fragment6
            getSupportFragmentManager().beginTransaction().replace(R.id.homeframelayout,homeFragment).commit();

        } else if (item.getItemId() == R.id.navigation_menu_category) {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeframelayout,categoryFragment).commit();
        }
        else if(item.getItemId() == R.id.navigation_menu_myorder) {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeframelayout,myorderFragment).commit();
        }


        return true;
    }
}