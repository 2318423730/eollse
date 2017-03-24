package com.eollse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.app.MyApplication;

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
    @BindView(R.id.ll_left)
    LinearLayout llLeft;
    @BindView(R.id.activitySjxx)
    LinearLayout activitySjxx;
    @BindView(R.id.ll_right)
    LinearLayout llRight;
    @BindView(R.id.view_bg)
    View viewBg;

    private PopupWindow popupWindow;
    private View myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sjxx);
        ButterKnife.bind(this);
        tvTitle.setText("书记信箱");


        myView = getLayoutInflater().inflate(R.layout.popup_sjxx_success, null);
        popupWindow = null;
        popupWindow = new PopupWindow(myView, 500, 800, true);
    }

    @OnClick({R.id.tv_backHome, R.id.tv_back, R.id.btn_commit, R.id.btn_reset})
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
//        if ("".equals(etAddress.getText().toString())) {
//            MyToast.showToast(getApplicationContext(), "事件发生地点不能为空");
//            return;
//        }
//        if ("".equals(etTime.getText().toString())) {
//            MyToast.showToast(getApplicationContext(), "事件发生时间不能为空");
//            return;
//        }
//        if ("".equals(etPeople.getText().toString())) {
//            MyToast.showToast(getApplicationContext(), "事件参与人员不能为空");
//            return;
//        }
//        if ("".equals(etContent.getText().toString())) {
//            MyToast.showToast(getApplicationContext(), "事件描述不能为空");
//            return;
//        }

        showPopup(myView);
    }


    private void showPopup(View myView) {
        viewBg.setVisibility(View.VISIBLE);
        llRight.setVisibility(View.GONE);
        //页面居中
        popupWindow.showAtLocation(activitySjxx, Gravity.CENTER, 0, 0);
        Button btn_yes = (Button) myView.findViewById(R.id.btn_yes);
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewBg.setVisibility(View.GONE);
                llRight.setVisibility(View.VISIBLE);

                popupWindow.dismiss();

            }
        });
    }
}
