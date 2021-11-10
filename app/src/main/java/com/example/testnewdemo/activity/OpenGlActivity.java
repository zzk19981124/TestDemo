package com.example.testnewdemo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.testnewdemo.R;
import com.example.testnewdemo.adapter.OpenGlAdapter;
import com.example.testnewdemo.bean.OpenGlDemoList;
import com.example.testnewdemo.opengl.demo1.OpenGlDemoActivity1;
import com.example.testnewdemo.util.Utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * open GL es 2.0
 */
public class OpenGlActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private List<OpenGlDemoList> mData = null;
    private Context context;
    private OpenGlAdapter adapter = null;
    private String[] allName = new String[]{"创建第一个程序"};
    private ListView list;
    private List<Activity> activities = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_gl);
        initView();
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String text = "you click " + list.getItemAtPosition(i) + "";
        Utils.showToast(context, text);
        Intent intent = new Intent(this, activities.get(i).getClass());
        startActivity(intent);
    }

    private void initView() {
        context = this;
        list = findViewById(R.id.open_gl_list);
        mData = new ArrayList<>();
        activities = new ArrayList<Activity>(allName.length);
        OpenGlDemoActivity1 OpenGlDemoActivity1 = new OpenGlDemoActivity1();
        activities.add(OpenGlDemoActivity1);
        for (int i = 0; i < allName.length; i++) {
            mData.add(new OpenGlDemoList(allName[i]));
        }
        adapter = new OpenGlAdapter(context, mData);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
    }
}