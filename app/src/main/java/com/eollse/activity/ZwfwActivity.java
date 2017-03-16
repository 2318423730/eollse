package com.eollse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.adapter.ZwfwAdapter;
import com.eollse.app.MyApplication;
import com.eollse.entity.Zwfw;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 政务服务(二级页面)
 */
public class ZwfwActivity extends BaseActivity {

    @BindView(R.id.tv_backHome)
    TextView tvBackHome;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.gv_zwfw)
    GridView gvZwfw;

    private List<Zwfw> zwfwList=new ArrayList<>();
    private ZwfwAdapter zwfwAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zwfw);
        ButterKnife.bind(this);
        tvTitle.setText("政务服务");
        
        setListeners();
        getData();
        setAdapter();
    }

    private void setAdapter() {
        zwfwAdapter=new ZwfwAdapter(getApplicationContext(),zwfwList);
        gvZwfw.setAdapter(zwfwAdapter);
    }

    private void getData() {
        for (int i=0;i<20;i++){
            Zwfw zwfw=new Zwfw();
            zwfw.setIconId(R.drawable.zwfw_zcxx);
            zwfw.setTitle("政策信息");
            zwfwList.add(zwfw);
        }
    }

    private void setListeners() {
        //回首页
        tvBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ZwfwActivity.this, MainActivity.class);
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
    }


}
