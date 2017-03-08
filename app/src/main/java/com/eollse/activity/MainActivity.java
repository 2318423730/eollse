package com.eollse.activity;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.adapter.MainNewsAdapter;
import com.eollse.entity.MainNew;
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

    /**
     * 轮播文字
     */
    private String str = "";
    /**
     * 轮播数据集合
     */
    private List<String> pmdList;
    /**
     *新闻集合
     */
    private List<MainNew> mainNewList;
    /**
     * 主页新闻的适配器
     */
    private MainNewsAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        //设置轮播数据
        setPmdData();
        for (int i = 0; i < pmdList.size(); i++) {
            str += (pmdList.get(i) + "\t\t\t\t\t\t\t\t\t\t");
        }
        tvPmd.setText(str);

        //设置新闻数据
        setNews();
        //设置新闻适配器
        setAdapter();

    }
    /**
     * 设置新闻数据
     */
    private void setNews() {
        if(mainNewList==null){
            mainNewList=new ArrayList<>();
        }
        for(int i=0;i<10;i++){
            MainNew mainNew=new MainNew();
            mainNew.setTitle("新闻标题"+i);
            mainNew.setEditDate("2017-3-8");
            mainNew.setDeptName("区域");
            mainNewList.add(mainNew);
        }
    }

    /**
     * 设置新闻适配器
     */
    private void setAdapter() {
        adapter=new MainNewsAdapter(getApplicationContext(),mainNewList);
        lvListview.setAdapter(adapter);
    }

    /**
     *轮播数据
     */
    private void setPmdData() {
        pmdList = new ArrayList<>();
        pmdList.add("第一组aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        pmdList.add("第二组bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
        pmdList.add("第三组cccccccccccccccccccccccccccccccccccccccccccccccc");
        pmdList.add("第四组ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
        pmdList.add("第五组eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");

    }


}
