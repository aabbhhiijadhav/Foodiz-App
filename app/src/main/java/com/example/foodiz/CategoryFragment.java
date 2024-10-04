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

    ListView lvshowAllCategory,LVcategoryfragmentShowMultipleCategory2;
    TextView tvCategorynotAvilable,tv1;


    List<POJOCategoryDetail> pojoCategoryDetails;
    Adapter adapter;
    AdapterCategorywise adapterCategorywise;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
       // lvshowAllCategory = view.findViewById(R.id.LVcategoryfragmentShowMultipleCategory);

        pojoCategoryDetails=new ArrayList<>();

        tvCategorynotAvilable = view.findViewById(R.id.listnotavilable);
        lvshowAllCategory = view.findViewById(R.id.LVcategoryfragmentShowMultipleCategory);


        //creating the new mwthod

        GetAllCategory();


       // LVcategoryfragmentShowMultipleCategory2.setAdapter(adapterCategorywise);

        return view;
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