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
    /**
     * 跳转到其他页面时，上次视频播放到的进度
     */
    public static long lastPlayPosition;
    /**
     * 跳转到其他页面时，上次视频播放到的进度
     */
    public static int lastPosition;
    /**
    * 是否重新进入界面
    */
    public static boolean isRestart;
    /**
     * 联网工具类
     */
    public static OkHttpUtil okHttpUtil;

    @Override
    public void onCreate() {
        super.onCreate();
        okHttpUtil = OkHttpUtil.getInstance();

    }


}
