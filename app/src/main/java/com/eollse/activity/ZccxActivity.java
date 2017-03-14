package com.eollse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.app.MyApplication;

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
    @BindView(R.id.rg_top)
    RadioGroup rgTop;
    @BindView(R.id.horizontalScrollView)
    HorizontalScrollView horizontalScrollView;
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.iv_right)
    ImageView ivRight;


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
        for (int i = 1; i <=33; i++) {

            RadioButton tempButton = new RadioButton(this);
            //tempButton.setBackgroundResource(R.drawable.selector_top_title_background);// 设置RadioButton的背景图片
            tempButton.setButtonDrawable(android.R.color.transparent); // 设置按钮的样式
            tempButton.setPadding(20, 0, 20, 0);// 设置文字距离按钮四周的距离
            tempButton.setText("标题 " + i);
            tempButton.setTextColor(this.getResources().getColorStateList(R.color.selector_top_title_text));
            if (i==0){
                tempButton.setChecked(true);
            }
            rgTop.addView(tempButton, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        }

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

        rgTop.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkId) {

            }
        });
        ivRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // E/MyTAG: a=976
                // E/MyTAG: b=1770
                // E/MyTAG: c=88
                // E/MyTAG: d=11

                int a=horizontalScrollView.getMeasuredWidth();
                int b=rgTop.getMeasuredWidth();
                int c=b/rgTop.getChildCount();
                int d=b/c;
                Log.e("MyTAG","a="+a);
                Log.e("MyTAG","b="+b);
                Log.e("MyTAG","c="+c);
                Log.e("MyTAG","d="+d);
                int e=b/rgTop.getChildCount();
                Log.e("MyTAG","e="+e);


                if(count<e){
                    count++;

                    nowWidth+=a;
                }
                Log.e("MyTAG","nowWidth="+nowWidth);
                horizontalScrollView.scrollTo(nowWidth,horizontalScrollView.getMeasuredHeight());
            }
        });
        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a=horizontalScrollView.getMeasuredWidth();
                int b=rgTop.getMeasuredWidth();
                int c=b/rgTop.getChildCount();
                int d=b/c;
                Log.e("MyTAG","a="+a);
                Log.e("MyTAG","b="+b);
                Log.e("MyTAG","c="+c);
                Log.e("MyTAG","d="+d);

                int e=b/rgTop.getChildCount();

                if(count<=e && count>0){
                    count--;
                    nowWidth-=a;
                }
                if(nowWidth<0){
                    nowWidth=0;
                }

                Log.e("MyTAG","nowWidth="+nowWidth);
                horizontalScrollView.scrollTo(nowWidth,horizontalScrollView.getMeasuredHeight());
            }
        });

    }

    int nowWidth;
    int count=0;
}
