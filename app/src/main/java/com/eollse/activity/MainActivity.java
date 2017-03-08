package com.eollse.activity;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.ui.MyPmdTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {


    @BindView(R.id.ll_top1)
    LinearLayout llTop1;
    @BindView(R.id.iv_setting)
    ImageView ivSetting;
    @BindView(R.id.Address)
    TextView Address;
    @BindView(R.id.RealName)
    TextView RealName;
    @BindView(R.id.Mobile)
    TextView Mobile;
    @BindView(R.id.ll_top2)
    LinearLayout llTop2;
    @BindView(R.id.rl_top2)
    RelativeLayout rlTop2;
    @BindView(R.id.aaa)
    TextView aaa;
    @BindView(R.id.lv_listview)
    ListView lvListview;
    @BindView(R.id.ll_middle_left)
    LinearLayout llMiddleLeft;
    @BindView(R.id.ll_wyhs)
    LinearLayout llWyhs;
    @BindView(R.id.ll_zxcc)
    LinearLayout llZxcc;
    @BindView(R.id.ll_cxgs)
    LinearLayout llCxgs;
    @BindView(R.id.ll_pasq)
    LinearLayout llPasq;
    @BindView(R.id.ll_wyfw)
    LinearLayout llWyfw;
    @BindView(R.id.ll_hrhs)
    LinearLayout llHrhs;
    @BindView(R.id.ll_wybs)
    LinearLayout llWybs;
    @BindView(R.id.iv_zwfu)
    ImageView ivZwfu;
    @BindView(R.id.iv_shfw)
    ImageView ivShfw;
    @BindView(R.id.iv_add)
    ImageView ivAdd;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.tv_pmd)
    MyPmdTextView tvPmd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        tvPmd = (MyPmdTextView) findViewById(R.id.tv_pmd);

        setPmdData();


        for (int i = 0; i < pmdList.size(); i++) {
            str += (pmdList.get(i) + "\t\t\t\t\t\t\t\t\t\t");
        }
        tvPmd.setText(str);

    }

    private String str = "";
    private List<String> pmdList;

    private void setPmdData() {
        pmdList = new ArrayList<>();
        pmdList.add("第一组aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        pmdList.add("第二组bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
        pmdList.add("第三组cccccccccccccccccccccccccccccccccccccccccccccccc");
        pmdList.add("第四组ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
        pmdList.add("第五组eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");

    }


}
