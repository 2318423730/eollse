package com.eollse.activity.zwfw.dqfc;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.eollse.R;
import com.eollse.activity.BaseActivity;
import com.eollse.activity.MainActivity;
import com.eollse.activity.UrlActivity;
import com.eollse.adapter.DqhdInfoAdapter;
import com.eollse.adapter.DqhdUrlAdapter;
import com.eollse.app.MyApplication;
import com.eollse.entity.Dqhd;
import com.eollse.entity.DqhdHref;
import com.eollse.utils.Constants;
import com.eollse.utils.HttpCallBack;
import com.eollse.utils.MyLeftLinearLayout;
import com.eollse.utils.MyToast;

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
    @BindView(R.id.ll_myLeftLinearLayout)
    MyLeftLinearLayout llMyLeftLinearLayout;
    @BindView(R.id.materialRefreshLayout)
    MaterialRefreshLayout materialRefreshLayout;


    private List<Dqhd.DataBean> dqhdList = new ArrayList<>();
    private List<DqhdHref> dqhdHrefsList = new ArrayList<>();
    private DqhdInfoAdapter dqhdInfoAdapter;
    private DqhdUrlAdapter dqhdUrlAdapter;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constants.HANDLER_INFO_RECEIVED:
                    setInfoAdapter();
                    break;
                case Constants.HANDLER_NET_ERROR:
                    MyToast.showToast(getApplicationContext(), "网络不给力");

                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dqhd);
        ButterKnife.bind(this);
        tvTitle.setText("党群活动");


        getData();
        setListeners();


    }

    private void getData() {
        MyApplication.okHttpUtil.get(Constants.TEST_URL + "method=JSON", new HttpCallBack() {
            @Override
            public void OnSuccess(String jsonStr) {
                Dqhd dqhd = JSON.parseObject(jsonStr, Dqhd.class);
                dqhdList.clear();
                dqhdList.addAll(dqhd.getData());
                handler.sendEmptyMessage(Constants.HANDLER_INFO_RECEIVED);
            }

            @Override
            public void OnError(String jsonStr) {
                handler.sendEmptyMessage(Constants.HANDLER_NET_ERROR);//没有网络
                materialRefreshLayout.finishRefresh();//用于关闭刷新
            }
        });
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
//                Intent intent = new Intent(DqhdActivity.this, UrlActivity.class);
//                intent.putExtra("url", dqhdHrefsList.get(position).getUrl());
//                intent.putExtra("title", "党群活动");
//                startActivity(intent);
            }
        });
        llMyLeftLinearLayout.setBackZwfwActivity(this);
        //回首页
        tvBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DqhdActivity.this, MainActivity.class);
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
        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                dqhdList.clear();
                dqhdHrefsList.clear();
                getData();
            }
        });
    }

    private void setInfoAdapter() {
        if (dqhdInfoAdapter == null) {
            dqhdInfoAdapter = new DqhdInfoAdapter(DqhdActivity.this, dqhdList);
            gvInfo.setAdapter(dqhdInfoAdapter);
        } else {
            dqhdInfoAdapter.notifyDataSetChanged();
            if (dqhdList.size() > 0) {
                gvInfo.setSelection(0);
            }
        }

        for (int i = 0; i < dqhdList.size(); i++) {
            DqhdHref dqhdHref = new DqhdHref();
            dqhdHref.setTitle(dqhdList.get(i).getTitle());
            dqhdHref.setUrl("http://www.baidu.com");
            dqhdHrefsList.add(dqhdHref);
        }
        setUrlAdapter();

        materialRefreshLayout.finishRefresh();//用于关闭刷新
    }

    private void setUrlAdapter() {
        dqhdUrlAdapter = new DqhdUrlAdapter(getApplicationContext(), dqhdHrefsList);
        lvHref.setAdapter(dqhdUrlAdapter);
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
