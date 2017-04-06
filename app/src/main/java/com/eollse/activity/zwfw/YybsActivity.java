package com.eollse.activity.zwfw;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.activity.BaseActivity;
import com.eollse.activity.MainActivity;
import com.eollse.activity.zwfw.yybs.YybsSuccessActivity;
import com.eollse.adapter.YybsTypeAdapter;
import com.eollse.app.MyApplication;
import com.eollse.utils.Constants;
import com.eollse.utils.KCalendar;
import com.eollse.utils.MyLeftLinearLayout;
import com.eollse.utils.MyToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 预约办事
 */
public class YybsActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {


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
    @BindView(R.id.rb_time1_2)
    RadioButton rbTime12;
    @BindView(R.id.rb_time1_3)
    RadioButton rbTime13;
    @BindView(R.id.rb_time2_2)
    RadioButton rbTime22;
    @BindView(R.id.rb_time2_3)
    RadioButton rbTime23;
    @BindView(R.id.lv_type)
    ListView lvType;
    @BindView(R.id.et_userName)
    EditText etUserName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.btn_commit)
    Button btnCommit;
//    @BindView(R.id.iv_more)
//    ImageView ivMore;
    @BindView(R.id.ll_myLeftLinearLayout)
    MyLeftLinearLayout llMyLeftLinearLayout;


    private String date = null;// 设置默认选中的日期  格式为 “2014-04-05” 标准DATE格式


    private String time;//时间段
    private List<String> typeList = new ArrayList<>();
    private YybsTypeAdapter yybsTypeAdapter;
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

        setTypes();//设置类型
        //setTimes();//设置时间段
        popupwindowCalendarMonth.setText(popupwindowCalendar.getCalendarYear() + "年" + popupwindowCalendar.getCalendarMonth() + "月");
        setListeners();

    }

    private void setTypes() {
        typeList.add("商品房现售备案");
        typeList.add("建设工程招标备案");
        typeList.add("新建建筑节能认可");
        typeList.add("监理合同备案");
        typeList.add("建筑企业养老保障金管理");
        typeList.add("建设工程竣工结案备案");
        typeList.add("建设工程招标控制价备案");


        yybsTypeAdapter = new YybsTypeAdapter(getApplicationContext(), typeList);
        lvType.setAdapter(yybsTypeAdapter);
    }

    private void setTimes() {
//        timeList.add("9:00-10:30");
//        timeList.add("10:30-12:00");
//        timeList.add("13:00-14:30");
//        timeList.add("14:30-16:00");
//        timeList.add("16:00-18:00");
//        timeList.add("18:00-20:00");
//
//        yybsTimeAdapter = new YybsTimeAdapter(getApplicationContext(), timeList);
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

        llMyLeftLinearLayout.setBackZwfwActivity(this);
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

        rbTime11.setOnCheckedChangeListener(this);
        rbTime12.setOnCheckedChangeListener(this);
        rbTime13.setOnCheckedChangeListener(this);
        rbTime21.setOnCheckedChangeListener(this);
        rbTime22.setOnCheckedChangeListener(this);
        rbTime23.setOnCheckedChangeListener(this);

        lvType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                rgTime1.clearCheck();
                rgTime2.clearCheck();

                etUserName.setText("");
                etPhone.setText("");
                etCode.setText("");
                yybsTypeAdapter.setSelectIndex(position);
                yybsTypeAdapter.notifyDataSetChanged();
            }
        });
    }

    private void getData() {
        //nextMonth();

    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.rb_time1_1:
                rgTime2.clearCheck();
                time = rbTime11.getText().toString();
                break;
            case R.id.rb_time1_2:
                rgTime2.clearCheck();
                time = rbTime12.getText().toString();
                break;
            case R.id.rb_time1_3:
                rgTime2.clearCheck();
                time = rbTime13.getText().toString();
                break;
            case R.id.rb_time2_1:
                rgTime1.clearCheck();
                time = rbTime21.getText().toString();
                break;
            case R.id.rb_time2_2:
                rgTime1.clearCheck();
                time = rbTime22.getText().toString();
                break;
            case R.id.rb_time2_3:
                rgTime1.clearCheck();
                time = rbTime23.getText().toString();
                break;
        }

    }

    @OnClick({R.id.iv_previousMonth, R.id.iv_pnextMonth, R.id.btn_commit, R.id.btn_code})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_previousMonth://上个月
                popupwindowCalendar.lastMonth();
                break;
            case R.id.iv_pnextMonth://下个月
                popupwindowCalendar.nextMonth();
                break;
//            case R.id.iv_more:
//                moveListView();
//                break;
            case R.id.btn_commit:
                commit();
                break;
            case R.id.btn_code:
                getCode();
                break;
        }
    }

    private void getCode() {
        if ("".equals(etPhone.getText().toString())) {
            MyToast.showToast(getApplicationContext(), "电话不能为空");
            return;
        } else {
            MyToast.showToast(getApplicationContext(), "正在获取验证码");
        }
    }

    private void commit() {
        if ("".equals(date) || date == null) {
            MyToast.showToast(getApplicationContext(), "请选择日期");
            return;
        }
        if ("".equals(time) || time == null) {
            MyToast.showToast(getApplicationContext(), "请选择时间段");
            return;
        }
        if ("".equals(etUserName.getText().toString())) {
            MyToast.showToast(getApplicationContext(), "姓名不能为空");
            return;
        }
        if ("".equals(etCode.getText().toString())) {
            MyToast.showToast(getApplicationContext(), "验证码不能为空");
            return;
        }
        if ("".equals(etPhone.getText().toString())) {
            MyToast.showToast(getApplicationContext(), "电话不能为空");
            return;
        }
        //MyToast.showToast(getApplicationContext(), "预约成功");
        Intent intent=new Intent(YybsActivity.this, YybsSuccessActivity.class);
        startActivity(intent);
    }

//    private void moveListView() {
//        int i = lvType.getLastVisiblePosition();
//        if (i < typeList.size()) {
//            lvType.smoothScrollToPosition(i * 2 - 1);
//        } else {
//            lvType.smoothScrollToPosition(typeList.size());
//        }
//
//    }


}

