package com.eollse.activity.zwfw.bszn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.activity.BaseActivity;
import com.eollse.activity.MainActivity;
import com.eollse.adapter.BsznTitle1Adapter;
import com.eollse.adapter.BsznTitle2Adapter;
import com.eollse.app.MyApplication;
import com.eollse.entity.BsznTitle1;
import com.eollse.utils.MyLeftLinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 企业开办
 */
public class BsznItemActivity extends BaseActivity {


    @BindView(R.id.ll_myLeftLinearLayout)
    MyLeftLinearLayout llMyLeftLinearLayout;
    @BindView(R.id.tv_backHome)
    TextView tvBackHome;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.lv_maxTitle)
    ListView lvMaxTitle;
    @BindView(R.id.lv_minTitle)
    ListView lvMinTitle;
    private List<BsznTitle1> title1List = new ArrayList<>();
    private List<String> list = new ArrayList<>();
    private BsznTitle1Adapter bsznTitle1Adapter;
    private BsznTitle2Adapter bsznTitle2Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bszn_item);
        ButterKnife.bind(this);
        tvTitle.setText("企业开办");
        setListeners();

        for (int i = 0; i < 10; i++) {
            BsznTitle1 bsznTitle1 = new BsznTitle1();
            bsznTitle1.setTitle("企业开办");
            bsznTitle1.setDes("全部13项 审批类2项 服务类11项");
            bsznTitle1.setIconId(R.mipmap.ic_launcher);
            title1List.add(bsznTitle1);
        }

        for (int i = 0; i < 10; i++) {
            list.add("需要办理产品卫生许可");
        }

        bsznTitle1Adapter = new BsznTitle1Adapter(getApplicationContext(), title1List);
        bsznTitle2Adapter = new BsznTitle2Adapter(getApplicationContext(), list);
        lvMaxTitle.setAdapter(bsznTitle1Adapter);
        lvMinTitle.setAdapter(bsznTitle2Adapter);
    }

    private void setListeners() {
        llMyLeftLinearLayout.setBackZwfwActivity(this);
        lvMaxTitle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bsznTitle1Adapter.setSelectIndex(position);
                bsznTitle1Adapter.notifyDataSetChanged();
            }
        });
        lvMinTitle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bsznTitle2Adapter.setSelectIndex(position);
                bsznTitle2Adapter.notifyDataSetChanged();
            }
        });
    }


    @OnClick({R.id.tv_backHome, R.id.tv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_backHome:
                Intent intent = new Intent(BsznItemActivity.this, MainActivity.class);
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
