package com.eollse.app;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Environment;

import com.eollse.utils.OkHttpUtil;


import java.io.File;

import okhttp3.OkHttpClient;

/**
 * Created by miliang on 2017/3/7/0007.
 */

public class MyApplication extends Application {
    public static OkHttpUtil okHttpUtil;
    @Override
    public void onCreate() {
        super.onCreate();
        okHttpUtil= OkHttpUtil.getInstance();

    }


}
