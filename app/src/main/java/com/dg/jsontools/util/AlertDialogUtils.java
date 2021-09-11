package com.dg.jsontools.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class AlertDialogUtils {
    
   public static void show(String message,String title,String positiveButton,Activity activity){
      AlertDialog dialog = new AlertDialog.Builder(activity)
         .setTitle(title)
         .setMessage(message)
         .setPositiveButton(positiveButton, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dia, int which) {

            }
         })
         .setNegativeButton("", null)
         .create();
      dialog.show();
    }
    
}
