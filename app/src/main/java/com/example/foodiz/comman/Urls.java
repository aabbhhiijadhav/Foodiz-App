//class for the storing the all services
package com.example.foodiz.comman;

public class Urls {

    public static String WebServiceAddress = "http://192.168.10.216:80/FoodizAPI/";

    public static String LoginUserWebservice = WebServiceAddress + "Loginuser.php";
    public static String UserRegisterWebservices =  WebServiceAddress + "userregisterdata.php";

    public static String myDetailsWebService = WebServiceAddress + "myDetails.php";
    public static String ImageInServer = WebServiceAddress + "Images/";


    public static String GetCategoryAllDetails = WebServiceAddress + "categoryInfirmation.php";
}
