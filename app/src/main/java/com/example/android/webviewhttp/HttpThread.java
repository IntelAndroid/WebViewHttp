package com.example.android.webviewhttp;

import android.os.Handler;
import android.webkit.WebView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



/**
 * Created by Android on 2016/6/2.
 */
public class HttpThread extends Thread {
    String url;

    WebView webview;

    Handler handler;



    public HttpThread(String url, WebView webView, Handler handler) {
        this.handler = handler;
        this.url = url;
        this.webview = webView;
    }

    @Override
    public void run() {
        try {
            URL httpurl = new URL(url);
            try {
                HttpURLConnection connection = (HttpURLConnection) httpurl.openConnection();
                connection.setRequestMethod("GET");
                connection.setReadTimeout(5000);
                connection.connect();
                final StringBuffer buffer = new StringBuffer();
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(connection.getInputStream()));
               String str;
                while((str=reader.readLine())!=null){
                    buffer.append(str);
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        webview.loadData(buffer.toString(),"text/html;charset=utf-8",null);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }
}
