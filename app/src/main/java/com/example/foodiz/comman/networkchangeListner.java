package com.example.foodiz.comman;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;

import com.example.foodiz.R;

public class networkchangeListner extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (!networkConnection.isConnectedToNeternet(context)){
            AlertDialog.Builder alertdialogbuilder=new AlertDialog.Builder(context);

            View layout_dialog= LayoutInflater.from(context).inflate(R.layout.internetconnection,null);

            //Explanation of this line.
            //layout_dialog = this is a object of view class
            //LayoutInflater = this class is used for to calling the xml file from the layout and show on the java class
            //.from() = method is used for to show that on whic java class you waht to show the xml file
            //inflate() = this method is used for calling or storing the path where the xml file is stored

            alertdialogbuilder.setView(layout_dialog);

            //finding the id of a button the button is stored into the internet_Connection xml and this xml folder is stored into layout_dialog
            AppCompatButton retry_connection;
            retry_connection=layout_dialog.findViewById(R.id.retry_connection);
            AlertDialog alertDialog=alertdialogbuilder.create();
            alertDialog.show();
            alertDialog.setCanceledOnTouchOutside(false);

            retry_connection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.dismiss();
                    onReceive(context,intent);
                }
            });




        }else {
            Toast.makeText(context, "Your Internet is connected", Toast.LENGTH_SHORT).show();
        }
    }
}
