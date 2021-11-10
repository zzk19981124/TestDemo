package com.example.testnewdemo.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.testnewdemo.R;
import com.example.testnewdemo.bean.OpenGlDemoList;

import java.util.ArrayList;
import java.util.List;

public class OpenGlAdapter extends BaseAdapter {
    private Context mContext;
    private List<OpenGlDemoList> mData = new ArrayList<>();

    public OpenGlAdapter(Context mContext, List<OpenGlDemoList> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(mContext).inflate(R.layout.opengl_demo_layout,viewGroup,false);
        TextView demoName = view.findViewById(R.id.demo_name);
        demoName.setText(mData.get(i).getName());
        return view;
    }
}
