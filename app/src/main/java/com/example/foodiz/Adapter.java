package com.example.foodiz;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class Adapter extends BaseAdapter {

    public Adapter(List<POJOCategoryDetail> pojoCategoryDetails, Activity activity) {
        this.pojoCategoryDetails = pojoCategoryDetails;
        this.activity = activity;
    }

    List<POJOCategoryDetail> pojoCategoryDetails;
    Activity activity;




    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
