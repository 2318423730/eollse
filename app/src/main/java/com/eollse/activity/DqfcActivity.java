package com.eollse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
    private int img[];
    private String title[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dqfc);
        ButterKnife.bind(this);
        tvTitle.setText("党群风采");

        img = new int[]{R.drawable.dqfc_dqhd, R.drawable.dqfc_zyfw, R.drawable.dqfc_zsdx,
                R.drawable.dqfc_sjxx, R.drawable.dqfc_sdxf, R.drawable.dqfc_ffcl};
        title = new String[]{"党群活动", "志愿者服务", "掌上党校",
                "书记信箱", "时代先锋", "反腐倡廉",};

        getData();
        setAdapter();
        setListeners();
    }

    private void setAdapter() {
        dqfcAdapter=new DqfcAdapter(getApplicationContext(),dqfcList);
        gvDqfc.setAdapter(dqfcAdapter);
    }

    private void getData() {
        for(int i=0;i<img.length;i++){
            Dqfc dqfc=new Dqfc();
            dqfc.setIconId(img[i]);
            dqfc.setTitle(title[i]);
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

        gvDqfc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent=new Intent();
                if(position==0){
                    intent.setClass(DqfcActivity.this,DqhdActivity.class);
                }else if(position==1){
                    intent.setClass(DqfcActivity.this,UrlActivity.class);
                    intent.putExtra("url","http://www.baidu.com");
                    intent.putExtra("title","志愿者服务");
                }else if(position==2){
                    intent.setClass(DqfcActivity.this,UrlActivity.class);
                    intent.putExtra("url","http://www.baidu.com");
                    intent.putExtra("title","掌上党校");
                }else if(position==3){
                    intent.setClass(DqfcActivity.this,SjxxActivity.class);
                }else if(position==4){
                    intent.setClass(DqfcActivity.this,SdxfActivity.class);
                }else{
                    return;
                }
                startActivity(intent);
            }
        });
    }

}
