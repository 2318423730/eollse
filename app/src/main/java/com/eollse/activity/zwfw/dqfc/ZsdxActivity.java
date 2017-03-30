package com.eollse.activity.zwfw.dqfc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.activity.BaseActivity;
import com.eollse.activity.MainActivity;
import com.eollse.activity.UrlActivity;
import com.eollse.adapter.ZsdxAdapter;
import com.eollse.app.MyApplication;
import com.eollse.utils.MyLeftLinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 掌上党校
 */
public class ZsdxActivity extends BaseActivity {

    @BindView(R.id.tv_backHome)
    TextView tvBackHome;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_dxdt)
    TextView tvDxdt;
    @BindView(R.id.tv_pxxt)
    TextView tvPxxt;
    @BindView(R.id.tv_kczx)
    TextView tvKczx;
    @BindView(R.id.tv_llxx)
    TextView tvLlxx;
    @BindView(R.id.tv_jczs)
    TextView tvJczs;
    @BindView(R.id.gv_right)
    GridView gvRight;
    @BindView(R.id.ll_myLeftLinearLayout)
    MyLeftLinearLayout llMyLeftLinearLayout;

    private List<Integer> list = new ArrayList<>();
    private ZsdxAdapter zsdxAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zsdx);
        ButterKnife.bind(this);
        tvTitle.setText("掌上党校");

        list.add(R.drawable.zsdx_1);
        list.add(R.drawable.zsdx_2);
        list.add(R.drawable.zsdx_3);
        list.add(R.drawable.zsdx_4);
        zsdxAdapter = new ZsdxAdapter(getApplicationContext(), list);
        gvRight.setAdapter(zsdxAdapter);

        setListeners();
    }

    private void setListeners() {
        llMyLeftLinearLayout.setBackZwfwActivity(this);
        gvRight.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.setClass(ZsdxActivity.this, UrlActivity.class);
                intent.putExtra("url", "http://www.baidu.com");
                startActivity(intent);
            }
        });
    }

    @OnClick({R.id.tv_backHome, R.id.tv_back, R.id.tv_dxdt, R.id.tv_pxxt, R.id.tv_kczx, R.id.tv_llxx, R.id.tv_jczs})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.tv_backHome:
                intent.setClass(ZsdxActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                MyApplication.clearAllActivitiesWithOutMainActivity();
                break;
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_dxdt:
                intent.setClass(ZsdxActivity.this, UrlActivity.class);
                intent.putExtra("url", "http://www.baidu.com");
                startActivity(intent);
                break;
            case R.id.tv_pxxt:
                intent.setClass(ZsdxActivity.this, UrlActivity.class);
                intent.putExtra("url", "http://www.baidu.com");
                startActivity(intent);
                break;
            case R.id.tv_kczx:
                intent.setClass(ZsdxActivity.this, UrlActivity.class);
                intent.putExtra("url", "http://www.baidu.com");
                startActivity(intent);
                break;
            case R.id.tv_llxx:
                intent.setClass(ZsdxActivity.this, UrlActivity.class);
                intent.putExtra("url", "http://www.baidu.com");
                startActivity(intent);
                break;
            case R.id.tv_jczs:
                intent.setClass(ZsdxActivity.this, UrlActivity.class);
                intent.putExtra("url", "http://www.baidu.com");
                startActivity(intent);
                break;
        }
    }
}
