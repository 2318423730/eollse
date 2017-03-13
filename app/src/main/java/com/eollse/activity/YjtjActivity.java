package com.eollse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.app.MyApplication;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 意见提交(三级页面)
 */
public class YjtjActivity extends BaseActivity {

    @BindView(R.id.tv_backHome)
    TextView tvBackHome;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.btn_tongyi)
    Button btnTongyi;
    @BindView(R.id.btn_butongyi)
    Button btnButongyi;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yjtj);
        ButterKnife.bind(this);
        tvTitle.setText("意见提交");

        //设置监听器
        setListeners();
    }

    private void setListeners() {
        //回首页
        tvBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(YjtjActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
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
        btnTongyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(YjtjActivity.this, YjtjContentActivity.class);
                startActivity(intent);
            }
        });
    }
}
