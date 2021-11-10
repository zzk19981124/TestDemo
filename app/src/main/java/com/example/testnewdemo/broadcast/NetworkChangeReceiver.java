package com.example.testnewdemo.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;

/**
 * 监听网络状态变化
 */
public class NetworkChangeReceiver extends BroadcastReceiver {
    private Context mContext;

    private static final String TAG = "NetworkChangeReceiver";

    public NetworkChangeReceiver(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 获取网络类型
     *
     * @param type
     * @return
     */
    private String getConnnectionType(int type) {
        String connType = "";
        if (type == ConnectivityManager.TYPE_MOBILE) {
            connType = "手机网络";
        } else if (type == ConnectivityManager.TYPE_WIFI) {
            connType = "wifi网络";
        }
        return connType;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "wifiState: ");
        if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction())) {
            int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0);
            Log.d(TAG, "wifiState: " + wifiState);
            switch (wifiState) {
                case WifiManager.WIFI_STATE_DISABLED:
                    Log.d(TAG, "wifi已关闭");
                    break;
                case WifiManager.WIFI_STATE_DISABLING:
                    Log.d(TAG, "wifi正在关闭");
                    break;
            }
        }
        //监听网络连接，包括wifi和移动数据的打开和关闭，以及连接上可用的连接都会监听到
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            //获取联网状态
            NetworkInfo netState = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
            if(netState != null) {
                //如果当前网络连接成功并且可用
                if (netState.getState() == NetworkInfo.State.CONNECTED && netState.isAvailable()) {
                    if (netState.getType()==ConnectivityManager.TYPE_WIFI||netState.getType()==ConnectivityManager.TYPE_MOBILE){
                        Log.d(TAG, getConnnectionType(netState.getType()) + "已连上");
                        showToast(getConnnectionType(netState.getType()) + "已连上");
                    }

                }else{
                    Log.d(TAG, getConnnectionType(netState.getType())+"已断开");
                    showToast(getConnnectionType(netState.getType()) + "已断开");
                }
            }
        }
    }
    public void showToast(String text){
        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
    }
}
















