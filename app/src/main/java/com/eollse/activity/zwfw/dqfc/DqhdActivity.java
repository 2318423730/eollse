package com.eollse.activity.zwfw.dqfc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.activity.BaseActivity;
import com.eollse.activity.MainActivity;
import com.eollse.activity.UrlActivity;
import com.eollse.adapter.DqhdInfoAdapter;
import com.eollse.adapter.DqhdUrlAdapter;
import com.eollse.app.MyApplication;
import com.eollse.entity.Dqhd;
import com.eollse.entity.DqhdHref;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 党群活动
 */
public class DqhdActivity extends BaseActivity {

    @BindView(R.id.tv_backHome)
    TextView tvBackHome;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.gv_info)
    GridView gvInfo;
    @BindView(R.id.lv_href)
    ListView lvHref;


    private String[] title;
    private int[] img;
    private List<Dqhd> dqhdList = new ArrayList<>();
    private List<DqhdHref> dqhdHrefsList = new ArrayList<>();
    private DqhdInfoAdapter dqhdInfoAdapter;
    private DqhdUrlAdapter dqhdUrlAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dqhd);
        ButterKnife.bind(this);
        tvTitle.setText("党群活动");

        title = new String[]{"在党群活动中心开展\"两学一做\"学习交流会", "亦庄党群活动中心实地参观学习", "莅临富森美家居党群活动中心开展活动",
                "在党群活动中心开展\"两学一做\"学习交流会", "亦庄党群活动中心实地参观学习", "莅临富森美家居党群活动中心开展活动"};
        img = new int[]{R.drawable.dqhd_1, R.drawable.dqhd_2, R.drawable.dqhd_3,
                R.drawable.dqhd_4, R.drawable.dqhd_5, R.drawable.dqhd_6};
        for (int i = 0; i < title.length; i++) {
            Dqhd dqhd = new Dqhd();
            dqhd.setIconId(img[i]);
            dqhd.setTitle(title[i]);
            dqhdList.add(dqhd);
        }
        for (int i = 0; i < 20; i++) {
            DqhdHref dqhdHref = new DqhdHref();
            dqhdHref.setTitle("链接地址");
            dqhdHref.setUrl("http://www.baidu.com");
            dqhdHrefsList.add(dqhdHref);
        }

        setInfoAdapter();
        setUrlAdapter();
        setListeners();


    }

    private void setUrlAdapter() {
        dqhdUrlAdapter = new DqhdUrlAdapter(getApplicationContext(), dqhdHrefsList);
        lvHref.setAdapter(dqhdUrlAdapter);
    }

    private void setListeners() {
        gvInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(DqhdActivity.this, UrlActivity.class);
                intent.putExtra("url", "http://www.baidu.com");
                startActivity(intent);
            }
        });

        lvHref.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(DqhdActivity.this, UrlActivity.class);
                intent.putExtra("url", dqhdHrefsList.get(position).getUrl());
                intent.putExtra("title", "党群活动");
                startActivity(intent);
            }
        });
    }

    private void setInfoAdapter() {
        dqhdInfoAdapter = new DqhdInfoAdapter(getApplicationContext(), dqhdList);
        gvInfo.setAdapter(dqhdInfoAdapter);
    }

    @OnClick({R.id.tv_backHome, R.id.tv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_backHome:
                Intent intent = new Intent(DqhdActivity.this, MainActivity.class);
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
