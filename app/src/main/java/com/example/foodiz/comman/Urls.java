//class for the storing the all services
package com.example.foodiz.comman;

public class Urls {

    public static String WebServiceAddress = "http://172.20.10.2:80/FoodizAPI/";

    public static String LoginUserWebservice = WebServiceAddress + "Loginuser.php";
    public static String UserRegisterWebservices =  WebServiceAddress + "userregisterdata.php";
    public static String myDetailsWebService = WebServiceAddress + "myDetails.php";
    public static String ImageInServer = WebServiceAddress + "Images/";
    public static String GetCategoryAllDetails = WebServiceAddress + "categoryInfirmation.php";
    public static String GetCategoryWiseAllDetails = WebServiceAddress + "categoryWiseDish.php";
    public static String GetTranding_Dish = WebServiceAddress + "tranding_category.php";
    public static String update_profile = WebServiceAddress + "update_profile.php";
}
