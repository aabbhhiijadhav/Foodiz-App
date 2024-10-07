package com.example.foodiz;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodiz.comman.Urls;

import java.util.List;
import java.util.zip.Inflater;

//we are extends our class from the baseAdapter we are use the property of BASEADAPTER.
public class Adapter_tranding_Dish extends BaseAdapter {

    //we are creating the constructor to takingthe all data from the pojo class we are setting our data to list view using adapter class
    //adapter is used as the get and set our data between the pojo and xml side

    public Adapter_tranding_Dish(List<POJO_Fetching_treandingDish> pojoFetchingTreandingDishes, Activity activity) {
        this.pojoFetchingTreandingDishes = pojoFetchingTreandingDishes;
        this.activity = activity;
    }

    //creation of the object because our all data is stored int this pojo object
    List<POJO_Fetching_treandingDish> pojoFetchingTreandingDishes;
    Activity activity;








    @Override
    public int getCount() {
        return pojoFetchingTreandingDishes.size();
    }

    @Override
    public Object getItem(int position) {
        return pojoFetchingTreandingDishes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    //this is our important method for setting the all data to our xml page
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        //first we are create the object of our viewholder class for using of it into our class
        final viewholder holder;

        //then we enabling the service of the our activity for applying the operation on the activity using the LayoutInflater class
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);

        if (view == null){
            holder = new viewholder();

            //we are applying the xml file force fully to our java class.using the inflater method or inflater function.
            view=inflater.inflate(R.layout.tranding_category,null);

            //we are finding the all id which items are stored into our xml file.

            holder.restaurant_image=view.findViewById(R.id.restaurant_image);
            holder.restaurant_name=view.findViewById(R.id.restaurant_name);
            holder.restaurant_description=view.findViewById(R.id.restaurant_description);
            holder.restaurant_rating=view.findViewById(R.id.restaurant_rating);

            //we are setting the data to our xml file
            view.setTag(holder);


        }
        else {
           holder = (viewholder) view.getTag();
        }
        final POJO_Fetching_treandingDish obj = pojoFetchingTreandingDishes.get(position);

        holder.restaurant_name.setText(obj.getRestaurant_Name());
        holder.restaurant_rating.setText(obj.getDish_Rating());
        holder.restaurant_description.setText(obj.getDish_Discrepataion());


        //loading the image into our application using Glid dependency.
        //Glide .with() -: this function is used for in which activity we want to upload our image.
        Glide.with(activity)


                //.load() -: this function is used to load the image into our activity.
                .load(Urls.ImageInServer+obj.getDish_image())

                //skipMemoryCache() -: this function is used when an image is alerady present in image view it will be keep clean that image view.
                .skipMemoryCache(true)

                //error() -: this function is used if there is any problem to load the image into our image view.
                .error(R.drawable.loading_image)

                //into() -: this function used to in which image view we want to load our image.
                .into(holder.restaurant_image);



        return view;
    }

    //creation of inner class
    class viewholder{
        ImageView restaurant_image;
        TextView restaurant_name,restaurant_description,restaurant_rating;
        Button order_now_button;
    }
}


