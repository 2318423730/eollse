package com.eollse.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by miliang on 2017/3/15/0015.
 */

public class NetworkUtil {
    /**
     * 判断是否有网络
     * @param context
     * @return
     */
    public static boolean netWorkCheck(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null) {
            return true;
        }
        return false;
    }

}
