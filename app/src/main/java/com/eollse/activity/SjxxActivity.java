package com.eollse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.app.MyApplication;
import com.eollse.utils.MyToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 书记信箱
 */
public class SjxxActivity extends BaseActivity {

    @BindView(R.id.tv_backHome)
    TextView tvBackHome;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.et_time)
    EditText etTime;
    @BindView(R.id.et_people)
    EditText etPeople;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.btn_commit)
    Button btnCommit;
    @BindView(R.id.btn_reset)
    Button btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sjxx);
        ButterKnife.bind(this);
        tvTitle.setText("书记信箱");
    }

    @OnClick({R.id.tv_backHome,R.id.tv_back,R.id.btn_commit, R.id.btn_reset})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_backHome:
                Intent intent = new Intent(SjxxActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                MyApplication.clearAllActivitiesWithOutMainActivity();
                break;

            case R.id.tv_back:
                finish();
                break;

            case R.id.btn_commit:
                commit();
                break;
            case R.id.btn_reset:
                reset();
                break;
        }
    }

    private void reset() {
        etAddress.setText("");
        etTime.setText("");
        etPeople.setText("");
        etContent.setText("");
    }

    private void commit() {
        if("".equals(etAddress.getText().toString())){
            MyToast.showToast(getApplicationContext(),"事件发生地点不能为空");
            return;
        }
        if("".equals(etTime.getText().toString())){
            MyToast.showToast(getApplicationContext(),"事件发生时间不能为空");
            return;
        }
        if("".equals(etPeople.getText().toString())){
            MyToast.showToast(getApplicationContext(),"事件参与人员不能为空");
            return;
        }
        if("".equals(etContent.getText().toString())){
            MyToast.showToast(getApplicationContext(),"事件描述不能为空");
            return;
        }
        Intent intent=new Intent();
        startActivity(intent);
    }
}
