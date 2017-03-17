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

    private int imgWy[];
    private String titleWy[];
    private int imgBm[];
    private String titleBm[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shfw);
        ButterKnife.bind(this);
        tvTitle.setText("社会服务");

        imgWy = new int[]{R.drawable.shfw_wyxx, R.drawable.shfw_wygg, R.drawable.shfw_wyjf, R.drawable.shfw_gzbx,
                R.drawable.shfw_fzdb, R.drawable.shfw_dtaq};
        titleWy = new String[]{"物业信息", "物业公告", "物业缴费", "故障报修",
                "房租代办", "电梯安全"};
        imgBm = new int[]{R.drawable.shfw_sqds, R.drawable.shfw_sqhd, R.drawable.shfw_esjy, R.drawable.shfw_jtwx,
                R.drawable.shfw_znjj, R.drawable.shfw_kdds,R.drawable.shfw_jjbj,R.drawable.shfw_jyfw,
                R.drawable.shfw_jj,R.drawable.shfw_hfcx,R.drawable.shfw_gjxl,};
        titleBm = new String[]{"社区电商", "社区活动", "二手交易", "家庭维修",
                "智能家居", "快递代收", "家居保洁", "就业服务", "家教",
                "话费查询", "公交线路"};
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
        for (int i = 0; i < imgBm.length-1; i++) {
            Shfw shfw = new Shfw();
            shfw.setIconId(imgBm[i]);
            shfw.setTitle(titleBm[i]);
            bmfwList.add(shfw);
        }
        setBmfwAdapter();
    }



    private void getWyfwData() {
        wyfwList = new ArrayList<>();
        for (int i = 0; i < imgWy.length-1; i++) {
            Shfw shfw = new Shfw();
            shfw.setIconId(imgWy[i]);
            shfw.setTitle(titleWy[i]);
            wyfwList.add(shfw);
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
