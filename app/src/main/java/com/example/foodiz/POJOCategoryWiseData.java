package com.example.foodiz;

public class POJOCategoryWiseData {

    String id,category_name,restorant_name,dish_category,dish_image,dish_name,dish_prise,dish_rating,dish_offer,dish_descrapton;

    public String getId() {
        return id;

    }

    public void setId(String id) {
        this.id = id;

    }

    public String getCategory_name() {
        return category_name;

    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;

    }

    public String getRestorant_name() {
        return restorant_name;

    }

    public void setRestorant_name(String restorant_name) {
        this.restorant_name = restorant_name;

    }

    public String getDish_category() {
        return dish_category;

    }

    public void setDish_category(String dish_category) {
        this.dish_category = dish_category;

    }

    public String getDish_image() {
        return dish_image;

    }

    public void setDish_image(String dish_image) {
        this.dish_image = dish_image;

    }

    public String getDish_name() {
        return dish_name;

    }

    public void setDish_name(String dish_name) {
        this.dish_name = dish_name;

    }

    public String getDish_prise() {
        return dish_prise;

    }

    public void setDish_prise(String dish_prise) {
        this.dish_prise = dish_prise;

    }

    public String getDish_rating() {
        return dish_rating;

    }

    public void setDish_rating(String dish_rating) {
        this.dish_rating = dish_rating;

    }

    public String getDish_offer() {
        return dish_offer;

    }

    public void setDish_offer(String dish_offer) {
        this.dish_offer = dish_offer;

    }

    public String getDish_descrapton() {
        return dish_descrapton;

    }

    public void setDish_descrapton(String dish_descrapton) {
        this.dish_descrapton = dish_descrapton;

    }

    public POJOCategoryWiseData(String id, String category_name, String restorant_name, String dish_category, String dish_image, String dish_name, String dish_prise, String dish_rating, String dish_offer, String dish_descrapton) {
        this.id = id;
        this.category_name = category_name;
        this.restorant_name = restorant_name;
        this.dish_category = dish_category;
        this.dish_image = dish_image;
        this.dish_name = dish_name;
        this.dish_prise = dish_prise;
        this.dish_rating = dish_rating;
        this.dish_offer = dish_offer;
        this.dish_descrapton = dish_descrapton;
    }
}
