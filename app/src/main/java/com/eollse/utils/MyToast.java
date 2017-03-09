package com.eollse.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by miliang on 2017/3/9/0009.
 */

public class MyToast {
    public static void showToast(Context context,String str){
        Toast.makeText(context,str,Toast.LENGTH_SHORT).show();
    }
}
