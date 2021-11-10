package com.example.testnewdemo;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.example.testnewdemo.service.PhoneService;

public class MyApplication extends Application {
    private static final String TAG = "MyApplication";
    private PhoneService phoneService;
    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            phoneService = ((PhoneService.MyBinder) iBinder).getPhoneService();
            if (phoneService == null) {
                Log.d(TAG, "onServiceConnected:  -- null");
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Intent intent = new Intent(this,PhoneService.class);
        bindService(intent,connection,BIND_AUTO_CREATE);
        startService(intent);
    }
    public PhoneService getPhoneService(){
        if(phoneService!=null){
            return phoneService;
        }
        Log.d(TAG, "getPhoneService: -- null");
        return null;
    }
}
