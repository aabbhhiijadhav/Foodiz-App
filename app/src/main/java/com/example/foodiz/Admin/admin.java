package com.example.foodiz.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.foodiz.Admin.AdapterClass.AdminAdapter;
import com.example.foodiz.POJOCategoryDetail;
import com.example.foodiz.R;
import com.example.foodiz.comman.Urls;
import com.example.foodiz.viewallcustomerlocatation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class admin extends AppCompatActivity {


    RecyclerView adminrecycleview;
    List<POJOCategoryDetail> pojoCategoryDetailList;
    AdminAdapter adminAdapter;
    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


        cardView=findViewById(R.id.showuserlocation);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(admin.this, viewallcustomerlocatation.class));
            }
        });










        //creating the object of recycle view
        adminrecycleview=findViewById(R.id.adminrecycleview);

        //set the data is scrolled in application.
        adminrecycleview.setLayoutManager(new GridLayoutManager(admin.this,2,
                GridLayoutManager.HORIZONTAL,false));



        //create the extension of the pojo class
        pojoCategoryDetailList = new ArrayList<>();
        //create the extensition of the adapter class
        adminAdapter = new AdminAdapter(pojoCategoryDetailList,this);

        //set the data to the recycle viev
        adminrecycleview.setAdapter(adminAdapter);


        //seting the data to the

        getAllCategory();


    }

    private void getAllCategory() {
       //In volley libraries we will communcate with server using requestQueue function
        RequestQueue requestQueue = Volley.newRequestQueue(admin.this);


        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                Urls.GetCategoryAllDetails,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //when data will be get here the data will be in json object
                        //we will remove the json object from the String responce the remove then remove json array from json object and also remove the json object from json array

                        //Json object => Json Array => Json Object
                        try {
                            //we will get remove the jsonobject from responce
                            JSONObject jsonObject = new JSONObject(response);


                            //her we also remove the json array from the json object
                            JSONArray jsonArray = jsonObject.getJSONArray("categorydetail");

                            for (int i = 0 ; i < jsonArray.length() ; i++){
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                String strcategoryid = jsonObject1.getString("category_id");
                                String strcategoryname = jsonObject1.getString("categiryname");
                                String strcategoryimage = jsonObject1.getString("categoryimage");

                                pojoCategoryDetailList.add(new POJOCategoryDetail(strcategoryid,strcategoryimage,strcategoryname));
                            }


                            adminAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(admin.this, "Server Error", Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(stringRequest);
    }

}