package com.eollse.activity.zwfw.pasq;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.activity.BaseActivity;
import com.eollse.activity.MainActivity;
import com.eollse.app.MyApplication;
import com.eollse.utils.MyLeftLinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 社区监控
 */
public class SqjkActivity extends BaseActivity {

    @BindView(R.id.ll_myLeftLinearLayout)
    MyLeftLinearLayout llMyLeftLinearLayout;
    @BindView(R.id.tv_backHome)
    TextView tvBackHome;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.lv_area)
    ListView lvArea;
    @BindView(R.id.rg_type)
    RadioGroup rgType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqjk);
        ButterKnife.bind(this);
        tvTitle.setText("社区监控");

        rgType.check(R.id.rb_yulan);
        setListeners();
    }

    private void setListeners() {
        llMyLeftLinearLayout.setBackZwfwActivity(this);
    }

    @OnClick({R.id.ll_myLeftLinearLayout, R.id.tv_backHome, R.id.tv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_myLeftLinearLayout:
                break;
            case R.id.tv_backHome:
                Intent intent = new Intent(SqjkActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                MyApplication.clearAllActivitiesWithOutMainActivity();
                break;
            case R.id.tv_back:
                finish();
                break;
        }
    }
}
