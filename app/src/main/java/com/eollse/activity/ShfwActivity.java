package com.eollse.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.adapter.ShfwAdapter;
import com.eollse.app.MyApplication;
import com.eollse.entity.Shfw;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 社会服务
 */
public class ShfwActivity extends BaseActivity {

    @BindView(R.id.tv_backHome)
    TextView tvBackHome;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.rb_wyfw)
    RadioButton rbWyfw;
    @BindView(R.id.rb_bmfw)
    RadioButton rbBmfw;
    @BindView(R.id.rg_top)
    RadioGroup rgTop;
    @BindView(R.id.gv_wyfw)
    GridView gvWyfw;
    @BindView(R.id.gv_bmfw)
    GridView gvBmfw;
    /**
     * 物业服务集合
     */
    private List<Shfw> wyfwList;
    /**
     * 便民服务集合
     */
    private List<Shfw> bmfwList;
    private ShfwAdapter wyfwAdapter;
    private ShfwAdapter bmfwAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shfw);
        ButterKnife.bind(this);
        tvTitle.setText("社会服务");
        setListeners();
        getWyfwData();
        getBmfwData();

    }




    private void setListeners() {

        //回首页
        tvBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShfwActivity.this, MainActivity.class);
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
    private void getBmfwData() {


        bmfwList=new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Shfw shfw2 = new Shfw();
            shfw2.setIconId(R.drawable.sqds);
            shfw2.setTitle("社区电商");
            bmfwList.add(shfw2);
        }
        setBmfwAdapter();
    }



    private void getWyfwData() {
        wyfwList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Shfw shfw1 = new Shfw();
            shfw1.setIconId(R.drawable.wyxx);
            shfw1.setTitle("物业信息");
            wyfwList.add(shfw1);
        }
        setWyfwAdapter();
    }
    private void setWyfwAdapter() {
        wyfwAdapter=new ShfwAdapter(getApplicationContext(),wyfwList);
        gvWyfw.setAdapter(wyfwAdapter);
    }
    private void setBmfwAdapter() {
        bmfwAdapter=new ShfwAdapter(getApplicationContext(),bmfwList);
        gvBmfw.setAdapter(bmfwAdapter);
    }


}
