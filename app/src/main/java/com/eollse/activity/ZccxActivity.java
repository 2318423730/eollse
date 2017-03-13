package com.eollse.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.app.MyApplication;
import com.eollse.fragment.TabFragnemt;
import com.viewpagerindicator.TabPageIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 政策咨询
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
    @BindView(R.id.ll_topMenu)
    LinearLayout llTopMenu;

    //    @BindView(R.id.indicator)
//    TabPageIndicator indicator;
//    @BindView(R.id.viewPager)
//    ViewPager viewPager;
//    String[] tab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zczx);
        ButterKnife.bind(this);
        tvTitle.setText("政策资讯");
        setTopMenu();
        //设置监听器
        setListeners();
    }

    private void setTopMenu() {
        // 添加TextView控件
        // 参数设置
        LinearLayout.LayoutParams menuLinerLayoutParames =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1);
        for (int i = 0; i < 20; i++) {
            TextView tvMenu = new TextView(this);
            tvMenu.setLayoutParams(new ViewGroup.LayoutParams(30, 30));
            tvMenu.setPadding(30, 14, 30, 10);
            tvMenu.setText("标题" + i);
            tvMenu.setTextColor(Color.WHITE);
            tvMenu.setGravity(Gravity.CENTER_HORIZONTAL);
            llTopMenu.addView(tvMenu, menuLinerLayoutParames);
        }


//        tab = new String[10];
//        for (int i = 0; i < 20; i++) {
//            tab[i] = "标题" + i;
//        }


//        //ViewPager的adapter
//        FragmentPagerAdapter adapter = new TabPageIndicatorAdapter(getSupportFragmentManager());
//        viewPager.setAdapter(adapter);
//        //设置ViewPager与indicator关联
//        indicator.setViewPager(viewPager);
//        //如果我们要对ViewPager设置监听，用indicator设置就行了
//        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//
//            @Override
//            public void onPageSelected(int arg0) {
//               // Toast.makeText(getApplicationContext(), tab[arg0], Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onPageScrolled(int arg0, float arg1, int arg2) {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int arg0) {
//
//            }
//        });
    }

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

    }

//    private class TabPageIndicatorAdapter extends FragmentPagerAdapter {
//
//        public TabPageIndicatorAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            //新建一个Fragment来展示ViewPager item的内容，并传递参数
//            Fragment fragment = new TabFragnemt();
//            Bundle bundle = new Bundle();
//            bundle.putString("tabName", tab[position]);
//            fragment.setArguments(bundle);
//            return fragment;
//        }
//
//        @Override
//        public int getCount() {
//            return tab.length;
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return tab[position % tab.length];
//        }
//    }
}
