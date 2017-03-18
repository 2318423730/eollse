package com.eollse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.eollse.R;
import com.eollse.app.MyApplication;
import com.eollse.utils.Constants;
import com.eollse.utils.HttpCallBack;
import com.eollse.utils.SharedPreUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 意见提交(三级页面)
 */
public class SqtjActivity extends BaseActivity {

    @BindView(R.id.tv_backHome)
    TextView tvBackHome;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.btn_tongyi)
    Button btnTongyi;
    @BindView(R.id.btn_butongyi)
    Button btnButongyi;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;
    private String content;
    private String url;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case Constants.HANDLER_INFO_RECEIVED:
                    tvContent.setText(content);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqtj);
        ButterKnife.bind(this);
        tvTitle.setText("诉求提交");

        getData();
        //设置监听器
        setListeners();
    }

    private void getData() {

        url = Constants.BASE_URL + "?TVInfoId="+SharedPreUtil.getValue(this, "userinfo", "TVInfoId", "")+"&method=OpinionAgreement&Key="+SharedPreUtil.getValue(this, "userinfo", "Key", "");
        MyApplication.okHttpUtil.get(url, new HttpCallBack() {
            @Override
            public void OnSuccess(String jsonStr) {
                content = (String) JSON.parseObject(jsonStr).get("Agreement");
                handler.sendEmptyMessage(Constants.HANDLER_INFO_RECEIVED);
            }

            @Override
            public void OnError(String jsonStr) {

            }
        });
    }

    private void setListeners() {
        //回首页
        tvBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SqtjActivity.this, MainActivity.class);
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
        btnTongyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SqtjActivity.this, SqtjContentActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
