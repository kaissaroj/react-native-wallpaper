
package com.kaislibrary;


import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;




public class RNWalleModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

 private Callback rctCallback = null;
 Context context;
 String imageurl;
 String message = null;
  public RNWalleModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
     context = reactContext.getApplicationContext();
  }

  @Override
  public String getName() {
    return "RNWalle";
  }
  @ReactMethod
    public void setWallPaper(String imgUri,Callback callback) {
        rctCallback = callback;
        imageurl = imgUri;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                message = "failed";
                try  {
                    WallpaperManager myWallpaperManager
                            = WallpaperManager.getInstance(context);
                    try {
                        URL url = new URL(imageurl);
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setDoInput(true);
                        connection.connect();
                        InputStream input = connection.getInputStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(input);
                        if(bitmap!=null){
                            myWallpaperManager.setBitmap(bitmap);
                            message = "success";
                        }
                        rctCallback.invoke(message);
                    } catch (Exception e) {
                        message = e.getMessage();
                        rctCallback.invoke(message);
                    }
                } catch (Exception e) {
                    message = e.getMessage();
                    rctCallback.invoke(message);
                }
            }
        });
        thread.start();

    }

}