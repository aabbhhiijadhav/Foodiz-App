package com.example.foodiz.Admin.AdapterClass;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodiz.POJOCategoryDetail;
import com.example.foodiz.R;
import com.example.foodiz.comman.Urls;

import java.util.List;

public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.ViewHolder>{

    List<POJOCategoryDetail> pojoCategoryDetails;
    Activity activity;
    //created the constructure for taking the data at the timing of when object is created.
    public AdminAdapter(List<POJOCategoryDetail> pojoCategoryDetails, Activity activity) {
        this.pojoCategoryDetails = pojoCategoryDetails;
        this.activity = activity;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(activity).inflate(R.layout.admin_desigin,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        POJOCategoryDetail obj = pojoCategoryDetails.get(position);
        holder.ivcategoryname.setText(obj.getCategopryName());


        Glide.with(activity)
                .load(Urls.ImageInServer+obj.getCategoryImage())
                .skipMemoryCache(true)
                .error(R.drawable.imageloading)
                .into(holder.ivcategoryimage);
    }

    @Override
    public int getItemCount() {
        return pojoCategoryDetails.size();
    }

    //created inner class whose name is ViewHolder.
    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivcategoryimage;
        TextView ivcategoryname;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivcategoryimage = itemView.findViewById(R.id.ivcategoryimage);
            ivcategoryname = itemView.findViewById(R.id.tvcategoryname);
        }
    }
}
