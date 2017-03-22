package com.eollse.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.eollse.activity.BaseActivity;

/**
 * Created by miliang on 2017/3/9/0009.
 */

public class MyToast {
    public static void showToast(Context context,String str){
        Toast.makeText(context,str,Toast.LENGTH_SHORT).show();
    }


}
