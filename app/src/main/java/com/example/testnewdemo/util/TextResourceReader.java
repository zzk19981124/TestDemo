package com.example.testnewdemo.util;

import android.content.Context;
import android.content.res.Resources;
import android.media.ResourceBusyException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TextResourceReader {
    public static String readTextFileFromResource(Context context,int resourceId){
        StringBuilder body = new StringBuilder();
        try{
            InputStream is = context.getResources().openRawResource(resourceId);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String data;
            while((data=br.readLine())!=null){
                body.append(data);
                body.append('\n');
            }
        } catch (IOException e) {
            throw new RuntimeException(
                    "Could not open resource: "+resourceId,e
            );

        }catch (Resources.NotFoundException nfe){
            throw new RuntimeException("Resource not found: "+resourceId,nfe);
        }
        return body.toString();
    }
}
