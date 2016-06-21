package com.example.android.webviewhttp;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private WebView mWebView;
    Handler mHandler;
ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 网页加载
         */
//        setContentView(R.layout.activity_main);
//        mWebView = (WebView) findViewById(R.id.web_view);
//        mHandler = new Handler();
//        new HttpThread("http://www.hao123.com", mWebView, mHandler).start();
        /**
         * 图片加载
         */
//        setContentView(R.layout.image);
//        mImageView= (ImageView) findViewById(R.id.ioc);
//        mHandler=new Handler();
//        new HttpImage("http://pic1.xtuan.com/upload/image/20131212/13435115875_w.jpg",
//                mImageView,mHandler).start();
        //new getHttpclient(" http://118.244.212.82:9092/newsClient/news_list?ver=1&subid=1&dir=1&nid=1&stamp=20150520&cnt=20").start();
    }
}
