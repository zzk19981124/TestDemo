package com.example.testnewdemo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.testnewdemo.R;
import com.example.testnewdemo.util.NetworkUtils;
import com.example.testnewdemo.util.Utils;

import butterknife.BindView;

public class SimpleChatActivity extends AppCompatActivity {
    private static final String TAG = "SimpleChatActivity";
    private boolean isNetworkAvailable = false;
    private boolean isNetworkConnected = false;
    private Context mContext;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                  Utils.showToast(mContext,"网络连接不可用");
                  break;
//                case 1:
//                    Utils.showToast(mContext,"");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");
        isNetworkConnected = NetworkUtils.isNetworkConnected(this);
        mContext = this;
        if (isNetworkConnected){
            try {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        isNetworkAvailable = NetworkUtils.isOnline();
                        if (!isNetworkAvailable){
                            handler.sendEmptyMessage(0);
                        }
                        Log.d(TAG, "new Thread ---> isNetworkAvailable: "+isNetworkAvailable);
                    }
                }).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            handler.sendEmptyMessage(0);
        }

    }
}