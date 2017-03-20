package com.eollse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.adapter.BsznAdapter;
import com.eollse.app.MyApplication;
import com.eollse.entity.Bszn;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 办事指南(办事指南)
 */
public class BsznActivity extends BaseActivity {
    @BindView(R.id.tv_backHome)
    TextView tvBackHome;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.gv_bszn)
    GridView gvBszn;
    @BindView(R.id.rg_top)
    RadioGroup rgTop;
    @BindView(R.id.rb_geren)
    RadioButton rbGeren;
    @BindView(R.id.rb_qiye)
    RadioButton rbQiye;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    /**
     * 个人数据集合
     */
    private List<Bszn> bsznGerenList;
    /**
     * 企业数据集合
     */
    private List<Bszn> bsznQiyeList;
    private BsznAdapter bsznAdapter;
    /**
     * 用于设置adapeter的集合
     */
    private List<Bszn> list = new ArrayList<>();


    private int img[];
    private String title[];

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bszn);
        ButterKnife.bind(this);
        tvTitle.setText("办事指南");


        img = new int[]{R.drawable.bszn_aqsc, R.drawable.bszn_dwmy, R.drawable.bszn_gcjs, R.drawable.bszn_gysy,
                R.drawable.bszn_hyzy, R.drawable.bszn_hb, R.drawable.bszn_hysy, R.drawable.bszn_jt,
                R.drawable.bszn_jy, R.drawable.bszn_jyns, R.drawable.bszn_jy, R.drawable.bszn_mzzj,R.drawable.bszn_nsnj,R.drawable.bszn_qt};
        title = new String[]{"安全生产", "对外贸易", "工程建设", "公用事业",
                "行业准营", "环保", "婚育收养", "交通",
                "教育", "经营纳税", "就业", "名族宗教","年审年检",
                "其他"};

        //组装数据
        getData();
        //设置Adapter
        bsznAdapter = new BsznAdapter(getApplicationContext(), list);
        gvBszn.setAdapter(bsznAdapter);

        //设置监听
        setListeners();
    }

    private void setListeners() {
        //个人企业切换的监听
        rgTop.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
                switch (checkId) {
                    case R.id.rb_geren:
                        rgTop.check(R.id.rb_geren);
                        list.clear();
                        list.addAll(bsznGerenList);
                        bsznAdapter.notifyDataSetChanged();
                        break;
                    case R.id.rb_qiye:
                        rgTop.check(R.id.rb_qiye);
                        list.clear();
                        list.addAll(bsznQiyeList);
                        bsznAdapter.notifyDataSetChanged();
                        break;
                }
            }
        });
        //回首页
        tvBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BsznActivity.this, MainActivity.class);
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

    private void getData() {
        bsznGerenList = new ArrayList<>();
        bsznQiyeList = new ArrayList<>();
        for (int i = 0; i < img.length; i++) {
            Bszn bszn = new Bszn();
            bszn.setIconId(img[i]);
            bszn.setTitle(title[i]);
            bsznGerenList.add(bszn);

        }
        for (int i = 0; i < img.length; i++) {
            Bszn bszn = new Bszn();
            bszn.setIconId(img[i]);
            bszn.setTitle(title[i]);
            bsznQiyeList.add(bszn);
        }
        list.addAll(bsznGerenList);
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
