package com.eollse.app;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;


import com.eollse.activity.MainActivity;
import com.eollse.utils.OkHttpUtil;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.OkHttpClient;

/**
 * Created by miliang on 2017/3/7/0007.
 */

public class MyApplication extends Application {
    static{
        System.loadLibrary("stlport_shared");

    }
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
    private  CacheControl.Builder builder;
    //缓存
    public static CacheControl cache;
    @Override
    public void onCreate() {
        super.onCreate();
        okHttpUtil = OkHttpUtil.getInstance();
//        builder = new CacheControl.Builder();
//        builder.maxAge(10, TimeUnit.MILLISECONDS);//指示客户机可以接收生存期不大于指定时间的响应。
//        cache = builder.build();

    }

    public static List<Activity> activityList=new ArrayList<Activity>();

    /**
     * 清除MainActivity以外的Activity
     */
    public static void clearAllActivitiesWithOutMainActivity(){
        for(Activity activity:activityList){
            if(!(activity instanceof MainActivity)){
                activity.finish();
            }

        }
    }
    /**
     * 清除所有的Activity
     */
    public void exit(){
        //Log.d("MyTAG", "listsize="+activityList.size());
        for(Activity activity:activityList){
            activity.finish();
        }
        //myPid返回的是进程id
        android.os.Process.killProcess(android.os.Process.myPid());

    }

}
