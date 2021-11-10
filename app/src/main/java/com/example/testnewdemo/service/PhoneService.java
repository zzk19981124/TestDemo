package com.example.testnewdemo.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.testnewdemo.MessageEvent;
import com.example.testnewdemo.broadcast.NetworkChangeReceiver;
import com.example.testnewdemo.thread.Thread1;
import com.example.testnewdemo.thread.Thread2;
import com.example.testnewdemo.util.Utils;

import org.greenrobot.eventbus.EventBus;

public class PhoneService extends Service {
    private static final String TAG = "PhoneService";
    private boolean isReceivered = false;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
        System.out.println("Service is onCreate");
        Thread2 t2 = new Thread2();
        t2.start();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public class MyBinder extends Binder {
        public PhoneService getPhoneService() {
            return PhoneService.this;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
