package com.example.testnewdemo.opengl.demo1;

import static android.opengl.GLES20.*;
import android.content.Context;
import android.opengl.GLSurfaceView;
import com.example.testnewdemo.R;
import com.example.testnewdemo.util.ShaderHelper;
import com.example.testnewdemo.util.TextResourceReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * 渲染器 create by ak in 2021/11/11
 */
public class OpenGlDemo1Renderer implements GLSurfaceView.Renderer {
    private static final int POSTION_COMPONENT_COUNT = 2;//一个顶点有两个分量
    //把内存从java堆复制到本地堆
    private static final int BYTES_PER_FLOAT = 4;
    private final FloatBuffer vertexData;//用来在本地内存中存储数据
    private Context context;

    //逆时针的顺序画图，成为卷曲顺序
    public OpenGlDemo1Renderer(Context context) {
        this.context = context;
        float[] tableVerticesWithTriangles = {
                //triangle1
                0f, 0f,
                9f, 14f,
                0f, 14f,

                //triangle2
                0f, 0f,
                9f, 0f,
                9f, 14f,

                //line1
                0f, 7f,
                9f, 7f,

                //mallets
                4.5f, 2f,
                4.5f, 12f
        };
        vertexData = ByteBuffer
                .allocateDirect(tableVerticesWithTriangles.length * BYTES_PER_FLOAT)//分配本地内存
                .order(ByteOrder.nativeOrder())//按照本地字节序组织内容
                .asFloatBuffer();//希望使用浮点数
        vertexData.put(tableVerticesWithTriangles);
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        //红、绿、 蓝、alpha（透明度）
        glClearColor(0.0f, 1.0f, 0.0f, 0.0f);
        //读入着色器代码
        String vertexShadeSource = TextResourceReader.readTextFileFromResource(context, R.raw.simple_vertex_shander);
        String fragmentShaderSource = TextResourceReader.readTextFileFromResource(context,R.raw.simple_fragment_shader);
        //将着色器源代码上传到着色器对象里
        int vertexShaderObjectId = ShaderHelper.compileVertexShader(vertexShadeSource);
        int fragmentShaderObjectId = ShaderHelper.compileFragmentShader(fragmentShaderSource);
        //拿到着色器对象，接下来编译着色器源代码
        glShaderSource(vertexShaderObjectId,vertexShadeSource);
        glShaderSource(fragmentShaderObjectId,fragmentShaderSource);
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int i, int i1) {
        glViewport(0, 0, i, i1);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        //清空屏幕
        glClear(GL_COLOR_BUFFER_BIT);
    }

}
