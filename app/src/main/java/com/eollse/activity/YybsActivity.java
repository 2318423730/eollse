package com.eollse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.adapter.YybsTimeAdapter2;
import com.eollse.app.MyApplication;
import com.eollse.utils.Constants;
import com.eollse.utils.KCalendar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 预约办事
 */
public class YybsActivity extends BaseActivity {


    @BindView(R.id.tv_backHome)
    TextView tvBackHome;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.popupwindow_calendar_month)
    TextView popupwindowCalendarMonth;
    @BindView(R.id.popupwindow_calendar)
    KCalendar popupwindowCalendar;
    @BindView(R.id.iv_previousMonth)

    ImageView ivPreviousMonth;
    @BindView(R.id.iv_pnextMonth)
    ImageView ivPnextMonth;
    @BindView(R.id.rg_time1)
    RadioGroup rgTime1;
    @BindView(R.id.rg_time2)
    RadioGroup rgTime2;
    @BindView(R.id.rb_time1_1)
    RadioButton rbTime11;
    @BindView(R.id.rb_time2_1)
    RadioButton rbTime21;


    private String date = null;// 设置默认选中的日期  格式为 “2014-04-05” 标准DATE格式
    private YybsTimeAdapter2 yybsTimeAdapter;
    private List<String> timeList = new ArrayList<>();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constants.HANDLER_INFO_RECEIVED:


                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yybs);
        ButterKnife.bind(this);
        tvTitle.setText("预约办事");

        setTimes();//设置时间段
        popupwindowCalendarMonth.setText(popupwindowCalendar.getCalendarYear() + "年" + popupwindowCalendar.getCalendarMonth() + "月");
        setListeners();

    }

    private void setTimes() {
//        timeList.add("9:00-10:30");
//        timeList.add("10:30-12:00");
//        timeList.add("13:00-14:30");
//        timeList.add("14:30-16:00");
//        timeList.add("16:00-18:00");
//        timeList.add("18:00-20:00");
//
//        yybsTimeAdapter = new YybsTimeAdapter2(getApplicationContext(), timeList);
//        gvTime.setAdapter(yybsTimeAdapter);
//        //gvTime.setAdapter(yybsTimeAdapter);
//        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
//        //gridView形式
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
//        //瀑布流
//        //StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);
//        recyclerView.setLayoutManager(gridLayoutManager);
//        //设置分割线
//        //recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
//        recyclerView.setAdapter(yybsTimeAdapter);


    }

    private void setListeners() {


        //回首页
        tvBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(YybsActivity.this, MainActivity.class);
                startActivity(intent);

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
        //监听所选中的日期
        popupwindowCalendar.setOnCalendarClickListener(new KCalendar.OnCalendarClickListener() {
            @Override
            public void onCalendarClick(int row, int col, String dateFormat) {
                date = dateFormat;
                int month = Integer.parseInt(dateFormat.substring(dateFormat.indexOf("-") + 1, dateFormat.lastIndexOf("-")));

                if (popupwindowCalendar.getCalendarMonth() - month == 1//跨年跳转
                        || popupwindowCalendar.getCalendarMonth() - month == -11) {
                    popupwindowCalendar.lastMonth();

                } else if (month - popupwindowCalendar.getCalendarMonth() == 1 //跨年跳转
                        || month - popupwindowCalendar.getCalendarMonth() == -11) {
                    popupwindowCalendar.nextMonth();

                } else {

                    // popupwindowCalendar.addMarks(list, 0);
                    popupwindowCalendar.removeAllBgColor();
                    popupwindowCalendar.setCalendarDayBgColor(dateFormat, R.drawable.calendar_date_focused);
                    date = dateFormat;//最后返回给全局 date
                }
            }

        });

        //监听当前月份
        popupwindowCalendar.setOnCalendarDateChangedListener(new KCalendar.OnCalendarDateChangedListener() {
            public void onCalendarDateChanged(int year, int month) {
                popupwindowCalendarMonth.setText(year + "年" + month + "月");
            }
        });

        rgTime1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                rgTime2.check(R.id.rb_time2_1);
                rbTime21.setChecked(false);
            }
        });
        rgTime2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                rgTime1.check(R.id.rb_time1_1);
                rbTime11.setChecked(false);
            }
        });
    }

    private void getData() {
        //nextMonth();

    }


    @OnClick({R.id.iv_previousMonth, R.id.iv_pnextMonth})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_previousMonth://上个月
                popupwindowCalendar.lastMonth();
                break;
            case R.id.iv_pnextMonth://下个月
                popupwindowCalendar.nextMonth();

                break;
        }
    }
}
