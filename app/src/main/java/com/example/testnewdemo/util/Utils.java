package com.example.testnewdemo.util;

import android.content.Context;
import android.widget.Toast;

public class Utils {
    public static final int WIFI_OPEN = 0;
    public static final int WIFI_CLOSE = 1;
    public static final int SEND_NETWORK_BROADCAST = 10;
    public static void showToast(Context context,String text){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}
