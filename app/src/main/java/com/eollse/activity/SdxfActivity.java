package com.eollse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.adapter.SdxfAdapter;
import com.eollse.app.MyApplication;
import com.eollse.entity.Sdxf;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 时代先锋
 */
public class SdxfActivity extends BaseActivity {

    @BindView(R.id.tv_backHome)
    TextView tvBackHome;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.lv_left)
    ListView lvLeft;

    private List<Sdxf> list=new ArrayList<>();
    private SdxfAdapter sdxfAdapter;
    private String[] title;
    private int[] img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdxf);
        ButterKnife.bind(this);
        tvTitle.setText("时代先锋");



    }

    @OnClick({R.id.tv_backHome, R.id.tv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_backHome:
                Intent intent = new Intent(SdxfActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                MyApplication.clearAllActivitiesWithOutMainActivity();
                break;
            case R.id.tv_back:
                finish();
                break;
        }
    }
}
