package com.eollse.activity;

import android.app.Activity;
import android.os.Bundle;

import com.eollse.app.MyApplication;

/**
 * Created by miliang on 2017/3/7/0007.
 */

public class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.activityList.add(this);
    }
}
