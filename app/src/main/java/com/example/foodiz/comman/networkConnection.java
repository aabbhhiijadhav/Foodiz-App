//This class is only used for get the information only which network is connected with mobile

package com.example.foodiz.comman;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class networkConnection {


    //Method is used for check the applecation is connect to network or not
    public static boolean isConnectedToNeternet(Context context){


        //this class is used for faching the information about our application is connected to network or
        //this is go in a driver which store information about network and the stor into connectivity manager object

        ConnectivityManager connectivityManager=(ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);

        if(connectivityManager!=null){
            //this class is used for retrive the all information about networks which all are connected to our application
            //or android
            //here we provide the array to our networkInfo class because of multiple times our mobile is connected to mobile
            //network also wifi thats why we provide the array to our network class
            NetworkInfo[] networkInfo=connectivityManager.getAllNetworkInfo();

            if(networkInfo!=null){
                for (int i=0;i<networkInfo.length;i++){

                    //getstate()  method is used for to provide the state to of network to networkinfo oject
                    if (networkInfo[i].getState()==NetworkInfo.State.CONNECTED){
                        return true;
                    }
                }
            }
        }
        return  false;
    }

}
