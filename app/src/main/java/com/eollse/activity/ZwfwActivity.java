package com.eollse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
            if(i==0){
                zwfw.setTitle("政策信息");
            }else if(i==1){
                zwfw.setTitle("诉求提交");
            }else if(i==2){
                zwfw.setTitle("结果查询");
            }else if(i==5){
                zwfw.setTitle("党群风采");
            }else if(i==6){
                zwfw.setTitle("群团服务");
            }else if(i==7){
                zwfw.setTitle("网格管理");
            }else if(i==8){
                zwfw.setTitle("办事指南");
            }else if(i==11){
                zwfw.setTitle("社区概况");
            }else{
                zwfw.setTitle("政策信息");
            }

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

        gvZwfw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent();
                if(i==0){
                    //跳转到政策资讯
                    intent.setClass(ZwfwActivity.this,ZcxxActivity.class);
                 }else if(i==1){
                    //跳转到诉求提交
                    intent.setClass(ZwfwActivity.this,SqtjActivity.class);
                }else if(i==2){
                    ////跳转到结果查询

                }else if(i==5){
                    //跳转到党群风采
                    intent.setClass(ZwfwActivity.this,DqfcActivity.class);
                }else if(i==6){
                    //跳转到群团服务
                    intent.setClass(ZwfwActivity.this,QtfwActivity.class);
                }else if(i==7){
                    //跳转到网格管理
                    intent.setClass(ZwfwActivity.this,WgglActivity.class);
                }else if(i==8){
                    //跳转到办事指南
                    intent.setClass(ZwfwActivity.this,BsznActivity.class);
                }else if(i==11){
                    //跳转到社区概况
                    intent.setClass(ZwfwActivity.this,SqgkActivity.class);
                }
                startActivity(intent);
            }
        });
    }


}
