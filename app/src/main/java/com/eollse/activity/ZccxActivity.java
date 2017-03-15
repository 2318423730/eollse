package com.eollse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.eollse.R;
import com.eollse.adapter.HorizontalListViewAdapter;
import com.eollse.app.MyApplication;
import com.eollse.entity.Dept;
import com.eollse.ui.MyHorizontalListView;
import com.eollse.utils.Constants;
import com.eollse.utils.HttpCallBack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 政策信息
 */
public class ZccxActivity extends BaseActivity {

    @BindView(R.id.tv_backHome)
    TextView tvBackHome;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.lv_info)
    ListView lvInfo;
    @BindView(R.id.tv_previous)
    TextView tvPrevious;
    @BindView(R.id.tv_currentPage)
    TextView tvCurrentPage;
    @BindView(R.id.tv_totalPage)
    TextView tvTotalPage;
    @BindView(R.id.totalSize)
    TextView totalSize;
    @BindView(R.id.tv_next)
    TextView tvNext;
    //    @BindView(R.id.rg_top)
//    RadioGroup rgTop;
//    @BindView(R.id.horizontalScrollView)
//    HorizontalScrollView horizontalScrollView;


    @BindView(R.id.iv_right)
    ImageView ivRight;


    //    @BindView(R.id.indicator)
//    TabPageIndicator indicator;
//    @BindView(R.id.viewPager)
//    ViewPager viewPager;
    List<Dept.DataBean> tab;
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.myHorizontalListView)
    MyHorizontalListView myHorizontalListView;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case Constants.HANDLER_DEPT_RECEIVED:
                    setAdapter();
                    break;
            }
        }
    };

    private void setAdapter() {
        horizontalListViewAdapter = new HorizontalListViewAdapter(getApplicationContext(), tab);
        myHorizontalListView.setAdapter(horizontalListViewAdapter);
        horizontalListViewAdapter.setSelectIndex(0);
        horizontalListViewAdapter.notifyDataSetChanged();
        myHorizontalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                horizontalListViewAdapter.setSelectIndex(position);
                horizontalListViewAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zczx);
        ButterKnife.bind(this);
        tvTitle.setText("政策信息");
        setTopMenu();
        //设置监听器
        setListeners();
    }

    private void setTopMenu() {
//        // 添加TextView控件
//        // 参数设置
//        LinearLayout.LayoutParams menuLinerLayoutParames =
//                new LinearLayout.LayoutParams(
//                        LinearLayout.LayoutParams.WRAP_CONTENT,
//                        LinearLayout.LayoutParams.WRAP_CONTENT,
//                        1);
//        for (int i = 1; i <=33; i++) {
//
//            RadioButton tempButton = new RadioButton(this);
//            //tempButton.setBackgroundResource(R.drawable.selector_top_title_background);// 设置RadioButton的背景图片
//            tempButton.setButtonDrawable(android.R.color.transparent); // 设置按钮的样式
//            tempButton.setPadding(20, 0, 20, 0);// 设置文字距离按钮四周的距离
//            tempButton.setText("标题 " + i);
//            tempButton.setTextColor(this.getResources().getColorStateList(R.color.selector_top_title_text));
//            if (i==0){
//                tempButton.setChecked(true);
//            }
//            rgTop.addView(tempButton, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
//        }

        tab = new ArrayList();
//        for (int i = 0; i < 33; i++) {
//            tab.add("标题" + i);
//        }
        //TVInfoId=19&method=DeptList&Key=21218CCA77804D2BA1922C33E0151105
        Map<String,String> map=new HashMap<>();
        map.put("TVInfoId","19");
        map.put("method","DeptList");
        map.put("Key","21218CCA77804D2BA1922C33E0151105");
        MyApplication.okHttpUtil.post(Constants.BASE_URL, map, new HttpCallBack() {
            @Override
            public void OnSuccess(String jsonStr) {
                Dept dept= JSON.parseObject(jsonStr,Dept.class);
                tab=dept.getData();
                handler.sendEmptyMessage(Constants.HANDLER_DEPT_RECEIVED);
            }

            @Override
            public void OnError(String jsonStr) {

            }
        });

    }

    private HorizontalListViewAdapter horizontalListViewAdapter;

    private void setListeners() {
        //回首页
        tvBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ZccxActivity.this, MainActivity.class);
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

//        rgTop.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
//
//            }
//        });
        ivRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //int a = myHorizontalListView.getFirstVisiblePosition();
                //int b = myHorizontalListView.getLastVisiblePosition();
                //Log.e("MyTAG", "a=" + a + "   b=" + b + "   a-b=" + (b - a));
                //myHorizontalListView.setSelection(b - 1);
                myHorizontalListView.scrollTo(1000);



            }
        });
        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    myHorizontalListView.scrollTo(0);


            }
        });

    }

    int a=0;
}
