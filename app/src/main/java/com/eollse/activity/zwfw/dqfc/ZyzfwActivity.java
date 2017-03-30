package com.eollse.activity.zwfw.dqfc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.activity.BaseActivity;
import com.eollse.activity.MainActivity;
import com.eollse.activity.UrlActivity;
import com.eollse.adapter.ZyzfwInfoAdapter;
import com.eollse.app.MyApplication;
import com.eollse.entity.Zyzfw;
import com.eollse.ui.MyHorizontalListView;
import com.eollse.utils.MyLeftLinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 志愿者服务
 */
public class ZyzfwActivity extends BaseActivity {

    @BindView(R.id.tv_backHome)
    TextView tvBackHome;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_more1)
    TextView tvMore1;
    @BindView(R.id.lv_zyzx)
    MyHorizontalListView lvZyzx;
    @BindView(R.id.tv_more2)
    TextView tvMore2;
    @BindView(R.id.lv_zyhd)
    MyHorizontalListView lvZyhd;
    @BindView(R.id.ll_myLeftLinearLayout)
    MyLeftLinearLayout llMyLeftLinearLayout;

    private List<Zyzfw> list = new ArrayList<>();
    private ZyzfwInfoAdapter zyzfwZxAdapter;
    private ZyzfwInfoAdapter zyzfwHdAdapter;
    private int[] img;
    private String[] title;
    private String[] person;
    private String[] time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zyzfw);
        ButterKnife.bind(this);
        tvTitle.setText("志愿者服务");

        setListeners();
        img = new int[]{R.drawable.zyzfw_1, R.drawable.zyzfw_2, R.drawable.zyzfw_3, R.drawable.zyzfw_1};
        title = new String[]{"双凤桥街道长翔路社区市民学校开展元宵佳节送温暖活动", "双凤桥街道长翔路社区市民学校开展元宵佳节送温暖活动", "双凤桥街道长翔路社区市民学校开展元宵佳节送温暖活动", "双凤桥街道长翔路社区市民学校开展元宵佳节送温暖活动"};
        person = new String[]{"超级管理员", "超级管理员", "超级管理员", "超级管理员"};
        time = new String[]{"2017年3月28日", "2017年3月28日", "2017年3月28日", "2017年3月28日"};
        for (int i = 0; i < img.length; i++) {
            Zyzfw zyzfw = new Zyzfw();
            zyzfw.setIconId(img[i]);
            zyzfw.setTitle(title[i]);
            zyzfw.setPerson(person[i]);
            zyzfw.setTime(time[i]);
            list.add(zyzfw);
        }

        zyzfwZxAdapter = new ZyzfwInfoAdapter(getApplicationContext(), list);
        zyzfwHdAdapter = new ZyzfwInfoAdapter(getApplicationContext(), list);
        lvZyzx.setAdapter(zyzfwZxAdapter);
        lvZyhd.setAdapter(zyzfwHdAdapter);
    }

    private void setListeners() {
        llMyLeftLinearLayout.setBackZwfwActivity(this);
    }

    @OnClick({R.id.tv_backHome, R.id.tv_back, R.id.tv_more1, R.id.tv_more2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_backHome:
                Intent intent = new Intent(ZyzfwActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                MyApplication.clearAllActivitiesWithOutMainActivity();
                break;
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_more1:
                setUrl();
                break;
            case R.id.tv_more2:
                setUrl();
                break;
        }
    }

    private void setUrl() {
        Intent intent = new Intent(ZyzfwActivity.this, UrlActivity.class);
        intent.putExtra("url", "http://www.baidu.com");
        intent.putExtra("title", "志愿者服务");
        startActivity(intent);

    }
}
