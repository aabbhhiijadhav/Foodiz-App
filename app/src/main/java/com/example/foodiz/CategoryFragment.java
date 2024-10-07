package com.example.foodiz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodiz.comman.Urls;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


public class CategoryFragment extends Fragment {

    ListView lvshowAllCategory,tranding_dish_list;
    TextView tvCategorynotAvilable,tv1;


    List<POJOCategoryDetail> pojoCategoryDetails;
    Adapter adapter;

    //creating the next pojo class to fetcing multiple data from database.
    List<POJO_Fetching_treandingDish> pojoFetchingTreandingDishes;
    Adapter_tranding_Dish adapterTrandingDish;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        lvshowAllCategory = view.findViewById(R.id.LVcategoryfragmentShowMultipleCategory);




        tvCategorynotAvilable = view.findViewById(R.id.listnotavilable);
        tranding_dish_list=view.findViewById(R.id.LVcategoryfragmentShowMultipleCategory2);



        //create our pojo oblect as the type of arralist because it is holding the multiple data and then transfer all the data to pojo class
        pojoCategoryDetails = new ArrayList<>();
        pojoFetchingTreandingDishes = new ArrayList<>();


        //creating the new mwthod
        GetAllCategory();
        tranding_Dish();

        return view;
    }

    //this is a method or function to fetching the data from the data using the API and we used all the data tp the client side.
    private void tranding_Dish() {
        AsyncHttpClient client = new AsyncHttpClient();

        RequestParams params = new RequestParams();

        client.post(Urls.GetTranding_Dish,
                params,
                new JsonHttpResponseHandler()
                {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);

                        try {
                            JSONArray jsonArray = response.getJSONArray("tranding_data");
                            if (jsonArray == null){
                                tvCategorynotAvilable.setVisibility(View.VISIBLE);
                            }
                            else {
                                for (int i = 0 ; i < jsonArray.length() ; i++){
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String Restaurant_Name = jsonObject.getString("Restaurant_Name");
                                    String Dish_image = jsonObject.getString("Dish_image");
                                    String Dish_Rating = jsonObject.getString("Dish_Rating");
                                    String Dish_Discrepataion = jsonObject.getString("Dish_Discrepataion");


                                    //This is a
                                    pojoFetchingTreandingDishes.add(new POJO_Fetching_treandingDish(Restaurant_Name
                                    ,Dish_image
                                    ,Dish_Rating
                                    ,Dish_Discrepataion));

                                }
                                adapterTrandingDish = new Adapter_tranding_Dish(pojoFetchingTreandingDishes,getActivity());
                                tranding_dish_list.setAdapter(adapterTrandingDish);


                            }


                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }


                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                    }
                });



    }

    private void GetAllCategory() {
        //client server communication.
        AsyncHttpClient client = new AsyncHttpClient();

        RequestParams params = new RequestParams();

        client.post(Urls.GetCategoryAllDetails,
                params,
                new JsonHttpResponseHandler()
                {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        try {

                            //accpting the data from the php
                            JSONArray jsonArray = response.getJSONArray("categorydetail");

                            //check if our jsonarray is empty the we will set textview visible
                            if (jsonArray.isNull(0)){
                                tvCategorynotAvilable.setVisibility(View.VISIBLE);
                            }

                            //we are using for loop for
                            for (int i = 0 ; i < jsonArray.length() ; i++ ){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String id = jsonObject.getString("category_id");
                                String CategoryImage = jsonObject.getString("categoryimage");
                                String CategoryName = jsonObject.getString("categiryname");



                                pojoCategoryDetails.add(new POJOCategoryDetail(id,CategoryImage,CategoryName));
                            }

                            adapter=new Adapter(pojoCategoryDetails,getActivity());


                            lvshowAllCategory.setAdapter(adapter);




                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                        Toast.makeText(getActivity(),"Server Error", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}