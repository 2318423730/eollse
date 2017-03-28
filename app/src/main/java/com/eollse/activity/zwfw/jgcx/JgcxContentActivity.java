package com.eollse.activity.zwfw.jgcx;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.eollse.R;
import com.eollse.activity.BaseActivity;
import com.eollse.activity.MainActivity;
import com.eollse.adapter.JgcxContentAdapter;
import com.eollse.app.MyApplication;
import com.eollse.entity.JgcxContent;
import com.eollse.ui.MyListView;
import com.eollse.utils.Constants;
import com.eollse.utils.HttpCallBack;
import com.eollse.utils.SharedPreUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * 结果查询详情
 */
public class JgcxContentActivity extends BaseActivity {

    @BindView(R.id.tv_backHome)
    TextView tvBackHome;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_opinionId)
    TextView tvOpinionId;
    @BindView(R.id.tv_opinionClassName)
    TextView tvOpinionClassName;
    @BindView(R.id.tv_editDate)
    TextView tvEditDate;
    @BindView(R.id.tv_reDate)
    TextView tvReDate;
    @BindView(R.id.tv_auditState)
    TextView tvAuditState;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.lv_info)
    MyListView lvInfo;
    @BindView(R.id.tv_noText)
    TextView tvNoText;
    @BindView(R.id.tv_reContent)
    TextView tvReContent;

    private String url;



    private JgcxContent jgcxContent;
    private List<JgcxContent.DataBean> list=new ArrayList<>();
    private JgcxContentAdapter jgcxContentAdapter;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case Constants.HANDLER_INFO_RECEIVED:
                    setData();
                    setAdapter();
                    break;
            }
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jgcx_content);
        ButterKnife.bind(this);

        tvTitle.setText("结果查询");
//        if (getIntent().getStringExtra("OpinionClassId").equals("6")) {
//            title.setText("物业公示");
//        } else if (getIntent().getStringExtra("OpinionClassId").equals("7")) {
//            title.setText("事项评价");
//
//            if (getIntent().getStringExtra("AuditState").equals("1")) {
//                pjLayout.setVisibility(View.VISIBLE);
//            } else {
//                pjLayout.setVisibility(View.GONE);
//            }
//
//        }


        getData();

        setListeners();

    }

    private void getData() {
        url= Constants.BASE_URL+"TVInfoId="+ SharedPreUtil.getValue(this, "userinfo", "TVInfoId", "")+"&method=OpinionShow"+"&id="+getIntent().getStringExtra("OpinionId")+
                "&Key="+SharedPreUtil.getValue(this, "userinfo", "Key", "")+"&Action="+getIntent().getStringExtra("Action");
        MyApplication.okHttpUtil.get(url, new HttpCallBack() {
            @Override
            public void OnSuccess(String jsonStr) {
                jgcxContent= JSON.parseObject(jsonStr,JgcxContent.class);
                list.clear();
                list.addAll(jgcxContent.getData());

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
                Intent intent = new Intent(JgcxContentActivity.this, MainActivity.class);
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

    private void setData() {
        tvOpinionId.setText(jgcxContent.getOpinionId());//编号
        tvOpinionClassName.setText(jgcxContent.getOpinionClassName());//分类性质
        tvEditDate.setText(jgcxContent.getEditDate());//诉求时间
        tvReDate.setText(jgcxContent.getReDate());//办结时间
        tvAuditState.setText(jgcxContent.getAuditName());//办理状态
        tvContent.setText(jgcxContent.getContent());//诉求内容
        tvReContent.setText(jgcxContent.getReContent());//回复
    }
    private void setAdapter() {
        if(jgcxContentAdapter==null){
            jgcxContentAdapter=new JgcxContentAdapter(getApplicationContext(),list);
            lvInfo.setAdapter(jgcxContentAdapter);
        }else{
            jgcxContentAdapter.notifyDataSetChanged();
        }
    }
    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
