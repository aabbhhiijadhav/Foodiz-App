package com.example.foodiz;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodiz.comman.Urls;

import java.util.List;

public class Adapter extends BaseAdapter {

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
           view = inflater.inflate(R.layout.lv_get_all_category,null);

           holder.ivcategoryimage = view.findViewById(R.id.ivcategoryimage);
           holder.tvcaregoryname=view.findViewById(R.id.tvcategoryname);


           view.setTag(holder);

        }
        else {
            holder = (viewHolder) view.getTag();
        }

        final POJOCategoryDetail obj = pojoCategoryDetails.get(position);
        holder.tvcaregoryname.setText(obj.getCategopryName());

        Glide.with(activity)
                .load(Urls.ImageInServer+obj.getCategoryImage())
                .skipMemoryCache(true)
                .error(R.drawable.imageloading)
                .into(holder.ivcategoryimage);

        return view;



    }


    class viewHolder{
        ImageView ivcategoryimage;
        TextView tvcaregoryname;

    }

}
