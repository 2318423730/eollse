package com.eollse.activity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.adapter.WgglAdapter;
import com.eollse.entity.Wggl;
import com.eollse.utils.SharedPreUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 网格管理
 */
public class WgglActivity extends BaseActivity {

    @BindView(R.id.tv_backHome)
    TextView tvBackHome;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_Address)
    TextView tvAddress;
    @BindView(R.id.tv_RealName)
    TextView tvRealName;
    @BindView(R.id.tv_Mobile)
    TextView tvMobile;
    @BindView(R.id.gv_wggl)
    GridView gvWggl;

    private List<Wggl> wgglList=new ArrayList<>();
    private WgglAdapter wgglAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wggl);
        ButterKnife.bind(this);

        tvTitle.setText("网格管理");
        String address=SharedPreUtil.getValue(getApplicationContext(),"userinfo","Address","");
        String realName=SharedPreUtil.getValue(getApplicationContext(),"userinfo","RealName","");
        String mobile=SharedPreUtil.getValue(getApplicationContext(),"userinfo","Mobile","");
        tvAddress.setText("地址:"+address);
        tvRealName.setText("网格长:"+realName);
        tvMobile.setText("联系电话:"+mobile);
        
        getData();
        setAdapter();
    }

    private void setAdapter() {
        wgglAdapter =new WgglAdapter(getApplicationContext(),wgglList);
        gvWggl.setAdapter(wgglAdapter);
    }

    private void getData() {
        for(int i=0;i<20;i++){
            Wggl wggl=new Wggl();
            wggl.setIconId(R.drawable.ltjd);
            if(i==0){
                wggl.setTitle("龙塔街道");
            }else if(i==1){
                wggl.setTitle("网格信息人员");
            }else if(i==2){
                wggl.setTitle("统计分析");
            }else if(i==3){
                wggl.setTitle("工作管理");
            }else if(i==4){
                wggl.setTitle("人口管理");
            }else if(i==5){
                wggl.setTitle("基础数据");
            }else{
                wggl.setTitle("龙塔街道");
            }
            wgglList.add(wggl);
        }
    }
}
