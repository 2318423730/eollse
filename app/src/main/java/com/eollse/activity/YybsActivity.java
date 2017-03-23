package com.eollse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.app.MyApplication;
import com.eollse.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 预约办事
 */
public class YybsActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.tv_backHome)
    TextView tvBackHome;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_back)
    TextView tvBack;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constants.HANDLER_INFO_RECEIVED:


                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yybs);
        ButterKnife.bind(this);
        tvTitle.setText("预约办事");

        setListeners();

    }

    private void setListeners() {


        //回首页
        tvBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(YybsActivity.this, MainActivity.class);
                startActivity(intent);

                MyApplication.clearAllActivitiesWithOutMainActivity();
            }
        });
        //返回
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void getData() {

    }


    @Override
    public void onClick(View view) {

    }

}
