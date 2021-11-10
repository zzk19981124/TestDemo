package com.example.testnewdemo.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkUtils {

    private static final String TAG = "NetworkUtils";

    /*
        判断是否有网络连接,但如果该连接的网络无法上网，还是会返回true
    */
    public static boolean isNetworkConnected(Context context){
        if (context!=null){
            ConnectivityManager mConnectedManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = mConnectedManager.getActiveNetworkInfo();
            if (networkInfo!=null){
                return networkInfo.isAvailable();
            }
        }
        return false;
    }

    public static boolean isOnline(){
        URL url;
        try{
            url = new URL("https://www.baidu.com");
            InputStream inputStream = url.openStream();
            InputStreamReader isr = new InputStreamReader(inputStream,"utf-8");
            BufferedReader br = new BufferedReader(isr);
            String data = br.readLine();
            while(data != null){
                System.out.println(data);
                //Log.d(TAG, "data: "+data);
                data = br.readLine();
            }
            br.close();
            isr.close();
            inputStream.close();
            return true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
