package com.example.foodiz;

public class POJOCategoryDetail {
    //POJO plain object java oject
    //increase the reusability
    //POJO class gett multiplt data and set multiple data

    String ID,CategoryImage,CategopryName;

    public String getID() {

        return ID;
    }

    public void setID(String ID) {

        this.ID = ID;
    }

    public String getCategoryImage() {

        return CategoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        CategoryImage = categoryImage;
    }

    public String getCategopryName() {

        return CategopryName;
    }

    public void setCategopryName(String categopryName) {
        CategopryName = categopryName;
    }

    public POJOCategoryDetail(String ID, String categoryImage, String categopryName) {
        this.ID = ID;
        this.CategoryImage = categoryImage;
        this.CategopryName = categopryName;


    }
}
