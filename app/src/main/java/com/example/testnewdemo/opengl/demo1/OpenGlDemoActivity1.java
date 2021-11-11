package com.example.testnewdemo.opengl.demo1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.example.testnewdemo.util.Utils;

/**
 * openGL demo1
 *
 * 借鉴博客：https://www.jianshu.com/p/0701d9c7f01b
 *
 *
 * 1. 定义好物体的顶点，被读取到本地内存中，在绘制到屏幕的时候，需要通过管道（着色器）进行传输
 * 2. 着色器会告诉GPU如何处理绘制数据。分为顶点着色器、片段着色器
 */
public class OpenGlDemoActivity1 extends AppCompatActivity {
    private static final String TAG = "OpenGlDemoActivity1";
    private GLSurfaceView glSurfaceView;
    private boolean rendererSet = false;//记住 glSurfaceView 是否生效
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_open_gl_demo1);
        Log.d(TAG, "onCreate: ");
        glSurfaceView = new GLSurfaceView(this);
        setContentView(glSurfaceView);
        context = this;
        checkSystem();


    }

    //检查 系统 是否 支持 openGL es 2.0
    private void checkSystem() {
        final ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        final ConfigurationInfo info = am.getDeviceConfigurationInfo();
        final boolean supportEs2 = info.reqGlEsVersion >= 0x20000;
        if (supportEs2) {
            glSurfaceView.setEGLContextClientVersion(2);
            glSurfaceView.setRenderer(new OpenGlDemo1Renderer(context));
            rendererSet = true;
        } else {
            Utils.showToast(context, "该设备不支持OpenGl es 2.0");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (rendererSet) {
            glSurfaceView.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (rendererSet) {
            glSurfaceView.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}