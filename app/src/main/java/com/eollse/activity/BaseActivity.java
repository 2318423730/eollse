package com.eollse.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;

import com.eollse.app.MyApplication;
import com.eollse.utils.MyToast;
import com.eollse.utils.NetworkUtil;

/**
 * Created by miliang on 2017/3/7/0007.
 */

public class BaseActivity extends FragmentActivity {
    private AlertDialog dialog;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.activityList.add(this);

        boolean isNetWork = NetworkUtil.netWorkCheck(this);
        if (!isNetWork) {
            if(this instanceof MainActivity) {
                showDialog();
            }
//            }else{
//                MyToast.showToast(getApplicationContext(),"亲，当前没有网络连接!!!");
//            }

        }
    }

    private void showDialog() {
        builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("当前没有网络连接!!!");

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.dismiss();
            }
        });
        dialog = builder.create();
        dialog.show();
    }
}
