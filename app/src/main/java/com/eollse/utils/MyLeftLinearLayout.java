package com.eollse.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.activity.zwfw.ZwfwActivity;

/**
 * Created by Administrator on 2017/3/30.
 */

public class MyLeftLinearLayout extends LinearLayout implements View.OnClickListener {
    private TextView tv_title;
    private LinearLayout ll_left;


    public MyLeftLinearLayout(Context context) {
        super(context, null);
    }

    public MyLeftLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = View.inflate(context, R.layout.left, this);
        ll_left = (LinearLayout) view.findViewById(R.id.ll_left);
        tv_title = (TextView) view.findViewById(R.id.tv_leftTitle);
        tv_title.setText("政务服务");
        ll_left.setOnClickListener(this);
    }

    public MyLeftLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    public void setBackZwfwActivity(Activity activity) {

        this.activity = activity;

    }

    public void setLeftTitle(String str) {

        tv_title.setText(str);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_left:
                Intent intent = new Intent(activity, ZwfwActivity.class);
                activity.startActivity(intent);

                break;
        }
    }


    private Activity activity;
}
