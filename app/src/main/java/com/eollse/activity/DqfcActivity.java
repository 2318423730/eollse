package com.eollse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.adapter.DqfcAdapter;
import com.eollse.app.MyApplication;
import com.eollse.entity.Dqfc;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 党群风采
 */
public class DqfcActivity extends BaseActivity {

    @BindView(R.id.tv_backHome)
    TextView tvBackHome;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.gv_dqfc)
    GridView gvDqfc;

    private List<Dqfc> dqfcList=new ArrayList<>();
    private DqfcAdapter dqfcAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dqfc);
        ButterKnife.bind(this);
        tvTitle.setText("党群风采");
        
        getData();
        setAdapter();
        setListeners();
    }

    private void setAdapter() {
        dqfcAdapter=new DqfcAdapter(getApplicationContext(),dqfcList);
        gvDqfc.setAdapter(dqfcAdapter);
    }

    private void getData() {
        for(int i=0;i<20;i++){
            Dqfc dqfc=new Dqfc();
            dqfc.setIconId(R.drawable.dqfc_dqhd);
            dqfc.setTitle("党群活动");
            dqfcList.add(dqfc);
        }
    }

    private void setListeners() {
        //回首页
        tvBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DqfcActivity.this, MainActivity.class);
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
