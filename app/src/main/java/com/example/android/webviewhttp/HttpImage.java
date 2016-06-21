package com.example.android.webviewhttp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Android on 2016/6/2.
 */
public class HttpImage extends Thread {
    String murl;
    Handler mHandler;
    ImageView mImageView;
    private FileOutputStream mOutputStream;
    private File mDownloadfile;

    public HttpImage(String murl,ImageView imageView, Handler handler ) {
        this.murl = murl;
        mHandler = handler;
        mImageView = imageView;
    }

    @Override
    public void run() {
        try {
            URL url = new URL(murl);
            try {
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setReadTimeout(5000);
                connection.connect();
                InputStream inputStream = connection.getInputStream();

                String fileName = String.valueOf(System.currentTimeMillis());
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    File parent = Environment.getDownloadCacheDirectory();
                    mDownloadfile = new File(parent, fileName);
                    mOutputStream = new FileOutputStream(mDownloadfile);
                }
                byte[] bytes = new byte[1024 * 2];
                int len;
                if (mOutputStream != null) {
                    while ((len = inputStream.read(bytes)) != -1) {
                        mOutputStream.write(bytes, 0, len);
                    }
                }
               final Bitmap bitmap= BitmapFactory.decodeFile(mDownloadfile.getAbsolutePath());
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mImageView.setImageBitmap(bitmap);
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
