package com.example.foodiz;

public class POJO_Fetching_treandingDish {

    String Restaurant_Name,Dish_image,Dish_Rating,Dish_Discrepataion;

    public String getRestaurant_Name() {
        return Restaurant_Name;
    }

    public void setRestaurant_Name(String restaurant_Name) {
        Restaurant_Name = restaurant_Name;
    }

    public String getDish_image() {
        return Dish_image;
    }

    public void setDish_image(String dish_image) {
        Dish_image = dish_image;
    }

    public String getDish_Rating() {
        return Dish_Rating;
    }

    public void setDish_Rating(String dish_Rating) {
        Dish_Rating = dish_Rating;
    }

    public String getDish_Discrepataion() {
        return Dish_Discrepataion;
    }

    public void setDish_Discrepataion(String dish_Discrepataion) {
        Dish_Discrepataion = dish_Discrepataion;
    }

    public POJO_Fetching_treandingDish(String restaurant_Name, String dish_image, String dish_Rating, String dish_Discrepataion) {
        Restaurant_Name = restaurant_Name;
        Dish_image = dish_image;
        Dish_Rating = dish_Rating;
        Dish_Discrepataion = dish_Discrepataion;
    }
}
