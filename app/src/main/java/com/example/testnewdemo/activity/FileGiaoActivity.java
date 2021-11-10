package com.example.testnewdemo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.testnewdemo.R;

import butterknife.ButterKnife;

/**
 * 实现文件的存储的demo
 */
public class FileGiaoActivity extends AppCompatActivity {
    private static final String TAG = "FileGiaoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_giao);
        //KutterKnife.bind(this);

    }
}