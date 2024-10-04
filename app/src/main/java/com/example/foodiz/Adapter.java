package com.example.foodiz;

import static android.app.PendingIntent.getActivity;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
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

public class Adapter extends BaseAdapter {

    List<POJOCategoryWiseData> pojoCategoryWiseData;

    AdapterCategorywise adapterCategorywise;
    ListView  LVcategoryfragmentShowMultipleCategory2;


    public Adapter(List<POJOCategoryDetail> list, Activity activity) {
        this.pojoCategoryDetails = list;
        this.activity = activity;
    }

    List<POJOCategoryDetail> pojoCategoryDetails;
    Activity activity;


    @Override
    public int getCount() {

        //it will be written when pojo class get the data from the POJO and writen the size how many time this class will be execute
        return pojoCategoryDetails.size();
    }

    @Override
    public Object getItem(int position) {
        return pojoCategoryDetails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
       final viewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (view==null){
          holder = new viewHolder();
          //apply the xml file to the our java class.
           view = inflater.inflate(R.layout.lv_get_all_category,null);

           holder.ivcategoryimage = view.findViewById(R.id.ivcategoryimage);
           holder.tvcaregoryname=view.findViewById(R.id.tvcategoryname);
           holder.cvcategoryList = view.findViewById(R.id.cvcategoryList);


           view.setTag(holder);

        }
        else {
            holder = (viewHolder) view.getTag();
        }

        final POJOCategoryDetail obj = pojoCategoryDetails.get(position);

        holder.tvcaregoryname.setText(obj.getCategopryName());
//it will be used to accept the data from the database and load into our project
        Glide.with(activity)
                .load(Urls.ImageInServer+obj.getCategoryImage())
                .skipMemoryCache(true)
                .error(R.drawable.imageloading)
                .into(holder.ivcategoryimage);

        pojoCategoryWiseData=new ArrayList<>();
        LVcategoryfragmentShowMultipleCategory2=activity.findViewById(R.id.LVcategoryfragmentShowMultipleCategory2);

        holder.cvcategoryList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pojoCategoryWiseData.clear();
                String name = obj.getCategopryName();
            getcategorywisedata(name);


            }
        });

        return view;



    }

    private void getcategorywisedata(String name) {

        AsyncHttpClient client=new AsyncHttpClient();

        RequestParams params=new RequestParams();
        params.put("CategoryName",name);

        client.post(Urls.GetCategoryWiseAllDetails,
                params,
                new JsonHttpResponseHandler()
                {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        try {
                            JSONArray jsonArray = response.getJSONArray("categoryWiseDish");
                            for (int i = 0 ; i < jsonArray.length() ; i++){

                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String id = jsonObject.getString("id");
                                String category_name = jsonObject.getString("category_name");
                                String restorant_name = jsonObject.getString("restorant_name");
                                String dish_category = jsonObject.getString("dish_category");
                                String dish_image = jsonObject.getString("dish_image");
                                String dish_name = jsonObject.getString("dish_name");
                                String dish_prise = jsonObject.getString("dish_prise");
                                String dish_rating = jsonObject.getString("dish_rating");
                                String dish_offer = jsonObject.getString("dish_offer");
                                String dish_descrapton = jsonObject.getString("dish_descrapton");


                                pojoCategoryWiseData.add(new POJOCategoryWiseData(id,category_name,restorant_name,dish_category,
                                        dish_image,dish_name,dish_prise,dish_rating,dish_offer,dish_descrapton));


                            }
                             adapterCategorywise=new AdapterCategorywise(pojoCategoryWiseData,activity);
                            LVcategoryfragmentShowMultipleCategory2.setAdapter(adapterCategorywise);



                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                        Toast.makeText(activity, "Failer", Toast.LENGTH_SHORT).show();

                    }
                });
    }


    class viewHolder{
        CardView cvcategoryList;
        ImageView ivcategoryimage;
        TextView tvcaregoryname;
    }

}

