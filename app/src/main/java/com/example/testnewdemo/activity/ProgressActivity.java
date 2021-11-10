package com.example.testnewdemo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;

import com.example.testnewdemo.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *  socket study
 */
public class ProgressActivity extends AppCompatActivity {

    private static final String TAG = "ProgressActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        Log.d(TAG, "onCreate: ");
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(1000);
                TCpClient();
            }
        }).start();
    }
    protected void TCpClient(){
        try {
            Socket socket = new Socket(InetAddress.getLocalHost(),8888);
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            pw.print("客户端给服务端发送的数据");
            pw.flush();
            socket.shutdownOutput();

            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String data = null;
            while((data = br.readLine())!=null){
                System.out.println("客户端收到服务器回应的数据： "+ data);
            }
            socket.shutdownInput();

            br.close();
            isr.close();
            is.close();
            pw.close();
            os.close();
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}