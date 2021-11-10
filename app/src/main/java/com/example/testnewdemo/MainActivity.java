package com.example.testnewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Switch;

import com.example.testnewdemo.broadcast.NetworkChangeReceiver;
import com.example.testnewdemo.util.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Context mContext;
    //private static final int PORT  = 12245;
    private NetworkChangeReceiver networkReceiver;
    private boolean isReceiver = false;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                   // new NetworkChangeReceiver(mContext);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        mContext = this;
        setNetworkReceiver();
    }

    private void setNetworkReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        networkReceiver = new NetworkChangeReceiver(mContext);
        registerReceiver(networkReceiver,filter);
        isReceiver = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(isReceiver == true) {
            unregisterReceiver(networkReceiver);
            Log.d(TAG, "networkReceiver is close ");
        }
        EventBus.getDefault().unregister(this);

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getReceiverInfo(MessageEvent messageEvent){
        switch(messageEvent.getType()){
            case Utils.SEND_NETWORK_BROADCAST:
                //networkReceiver = new
                //handler.sendEmptyMessage(0);
                //setNetworkReceiver();
                new NetworkChangeReceiver(this);
                break;

        }
    }
}