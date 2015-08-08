package com.dk.remotecontrol.app.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by feng on 2015/7/18.
 */
public class ToastUtil {


    public static void longToast(Context context,String string){
        Toast.makeText(context,string,Toast.LENGTH_LONG).show();
    }

    public static void shortToast(Context context,String string){
        Toast.makeText(context,string,Toast.LENGTH_SHORT).show();
    }
}
