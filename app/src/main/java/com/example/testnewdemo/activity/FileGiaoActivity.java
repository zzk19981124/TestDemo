package com.example.testnewdemo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.testnewdemo.R;
import com.example.testnewdemo.helper.FileHelper;
import com.example.testnewdemo.util.Utils;

import butterknife.ButterKnife;

/**
 * 实现文件的存储的demo
 * <p>
 * 实现保存临时数据（在活动意外停止时）
 */
public class FileGiaoActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "FileGiaoActivity";
    private Context context;
    private Button inputBtn,deleteallBtn,readbtn;
    private EditText filenameEt,fileEmailEt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_giao);
        Log.d(TAG, "onCreate: ");
        //KutterKnife.bind(this);
        //addOther();
        context = this;
        bindView();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    public void bindView() {
        inputBtn = findViewById(R.id.file_input_btn);
        filenameEt = findViewById(R.id.filename);
        fileEmailEt = findViewById(R.id.file_email);
        deleteallBtn = findViewById(R.id.delete_all_edit_btn);
        readbtn = findViewById(R.id.file_output_btn);
        readbtn.setOnClickListener(this);
        deleteallBtn.setOnClickListener(this);
        inputBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.file_input_btn:
                FileHelper fileHelper = new FileHelper(context);
                String filename = filenameEt.getText().toString();
                String fileemail = fileEmailEt.getText().toString();
                try {
                    fileHelper.save(filename,fileemail);
                    Utils.showToast(context,"数据写入成功");
                } catch (Exception e) {
                    e.printStackTrace();
                    Utils.showToast(context,"数据写入失败");
                }
                break;
            case R.id.delete_all_edit_btn:
                filenameEt.setText("");
                fileEmailEt.setText("");
                Utils.showToast(context,"清除成功");
                break;
            case R.id.file_output_btn:

                break;
        }
    }

    private void addOther() {
        Button btn = findViewById(R.id.intent_empty);
        btn.setVisibility(View.VISIBLE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FileGiaoActivity.this, EmptyActivity.class);
                startActivity(i);
            }
        });
        addOther();
    }


}