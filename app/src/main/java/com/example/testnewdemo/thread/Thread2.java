package com.example.testnewdemo.thread;

import android.os.SystemClock;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 开启 socket 服务端
 */
public class Thread2 extends Thread {
    private static final String TAG = "Thread2";

    @Override
    public void run() {
        super.run();
        Log.d(TAG, "run: ");
        TCPServer();

    }

    /**
     * tcp  服务端
     */
    private void TCPServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            Log.d(TAG, "TCPServer: --- 1 ");
            Socket clientSocket = serverSocket.accept();
            Log.d(TAG, "TCPServer: --- 2 ");
            InputStream is = clientSocket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String data = null;
            while((data = br.readLine())!=null){
                System.out.println("服务端收到客户端的数据： "+data);
            }
            clientSocket.shutdownInput();
            OutputStream os = clientSocket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            pw.print("服务器给客户端回应的数据");
            pw.flush();
            //SystemClock.sleep(2000);
            clientSocket.shutdownOutput();
            pw.checkError();
            os.close();
            br.close();
            isr.close();
            is.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG, "TCPServer:   shutdown");
        }
    }
}
