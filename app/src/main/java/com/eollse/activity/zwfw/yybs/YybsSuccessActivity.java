package com.eollse.activity.zwfw.yybs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.activity.BaseActivity;
import com.eollse.activity.MainActivity;
import com.eollse.app.MyApplication;
import com.eollse.utils.MyLeftLinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class YybsSuccessActivity extends BaseActivity {

    @BindView(R.id.ll_myLeftLinearLayout)
    MyLeftLinearLayout llMyLeftLinearLayout;
    @BindView(R.id.tv_backHome)
    TextView tvBackHome;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.iv_success)
    ImageView ivSuccess;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.btn_yes)
    Button btnYes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commit_success);
        ButterKnife.bind(this);
        tvTitle.setText("预约办事");

        setListeners();
    }

    private void setListeners() {
        llMyLeftLinearLayout.setBackZwfwActivity(this);

    }


    @OnClick({R.id.tv_backHome, R.id.tv_back, R.id.btn_yes})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_backHome:Intent intent = new Intent(YybsSuccessActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                MyApplication.clearAllActivitiesWithOutMainActivity();

                break;
            case R.id.tv_back:
                finish();
                break;
            case R.id.btn_yes:
                finish();
                break;
        }
    }
}
