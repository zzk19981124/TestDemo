package com.example.testnewdemo.util;

import static android.opengl.GLES20.GL_FRAGMENT_SHADER;
import static android.opengl.GLES20.GL_VERTEX_SHADER;
import static android.opengl.GLES20.glCreateShader;

import android.opengl.GLES20;
import android.util.Log;

public class ShaderHelper {
    private static final String TAG = "ShaderHelper";

    public static int compileVertexShader(String shaderCode){
        //return compileVertexShader(GL_VERTEX_SHADER,)
        return compileShader(GL_VERTEX_SHADER,shaderCode);

    }
    public static int compileFragmentShader(String shaderCode){
        return compileShader(GL_FRAGMENT_SHADER,shaderCode);
    }
    private static int compileShader(int type,String shaderCode){
        final int shaderObjectId = glCreateShader(type);
        if (shaderObjectId == 0){
            if (LoggerConfig.ON){
                Log.w(TAG, "Could not create new shader." );
            }
        }
        return 0;
    }
}
