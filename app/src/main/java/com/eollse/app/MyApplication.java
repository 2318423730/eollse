package com.eollse.app;

import android.app.Application;

import com.eollse.utils.OkHttpUtil;

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
