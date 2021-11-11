package com.example.testnewdemo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.testnewdemo.R;

public class EmptyActivity extends AppCompatActivity {
    private static final String TAG = "EmptyActivity";
    boolean isClosed = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
        Log.d(TAG, "onCreate: ");
        if (savedInstanceState!=null){
            String tempData = savedInstanceState.getString("date_key");
            Log.d(TAG, tempData);
        }


    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        String tempData = "Something you just typed";
        outState.putString("data_key",tempData);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isClosed = true;
        Log.d(TAG, "onDestroy: ");
    }
}