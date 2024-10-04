package com.example.foodiz;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodiz.POJOCategoryWiseData;
import com.example.foodiz.R;
import com.example.foodiz.comman.Urls;

import java.util.List;

public class AdapterCategorywise extends BaseAdapter {
    List<POJOCategoryWiseData> pojoCategoryWiseData;
    Activity activity;

    public AdapterCategorywise(List<POJOCategoryWiseData> pojoCategoryWiseData, Activity activity) {
        this.pojoCategoryWiseData = pojoCategoryWiseData;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return pojoCategoryWiseData != null ? pojoCategoryWiseData.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return pojoCategoryWiseData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        final LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (view == null) {
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.second_xml_of_category_fragment_on_2listview, null);
            viewHolder.lv_iv_categorywiseimage = view.findViewById(R.id.lv_iv_categorywiseimage);
            //viewHolder.lv_iv_categorywisename = view.findViewById(R.id.lv_iv_categorywisename);
            viewHolder.lv_iv_categorywiserestorant = view.findViewById(R.id.lv_iv_categorywiserestorant);
           // viewHolder.lv_iv_categorywisedishcategory = view.findViewById(R.id.lv_iv_categorywisedishcategory);
            //viewHolder.lv_iv_categorywisedishname = view.findViewById(R.id.lv_iv_categorywisedishname);
            viewHolder.lv_iv_categorywisedishprise = view.findViewById(R.id.lv_iv_categorywisedishprise);
           // viewHolder.lv_iv_categorywisedishrating = view.findViewById(R.id.lv_iv_categorywisedishrating);
           // viewHolder.lv_iv_categorywisedishoffer = view.findViewById(R.id.lv_iv_categorywisedishoffer);
            //viewHolder.lv_iv_categorywisediscraption = view.findViewById(R.id.lv_iv_categorywisediscraption);

            view.setTag(viewHolder); // Set the tag here
        } else {
            viewHolder = (ViewHolder) view.getTag(); // Retrieve the ViewHolder from the tag
        }

        final POJOCategoryWiseData obj = pojoCategoryWiseData.get(i);
        //viewHolder.lv_iv_categorywisename.setText(obj.getCategory_name());
        viewHolder.lv_iv_categorywiserestorant.setText(obj.getRestorant_name());
        //viewHolder.lv_iv_categorywisedishcategory.setText(obj.getDish_category());
        //viewHolder.lv_iv_categorywisedishname.setText(obj.getDish_name());
        viewHolder.lv_iv_categorywisedishprise.setText(obj.getDish_prise());
       // viewHolder.lv_iv_categorywisedishrating.setText(obj.getDish_rating());
        //viewHolder.lv_iv_categorywisedishoffer.setText(obj.getDish_offer());
       // viewHolder.lv_iv_categorywisediscraption.setText(obj.getDish_descrapton());

        Glide.with(activity)
                .load(Urls.ImageInServer+obj.dish_image)
                .skipMemoryCache(true)
                .error(R.drawable.imageloading)
                .into(viewHolder.lv_iv_categorywiseimage);

        return view;
    }

    class ViewHolder {
        ImageView lv_iv_categorywiseimage;
        TextView lv_iv_categorywisename, lv_iv_categorywiserestorant, lv_iv_categorywisedishcategory, lv_iv_categorywisedishname,
                lv_iv_categorywisedishprise, lv_iv_categorywisedishrating, lv_iv_categorywisedishoffer, lv_iv_categorywisediscraption;
    }
}
