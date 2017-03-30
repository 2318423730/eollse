package com.eollse.activity.zwfw;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.activity.BaseActivity;
import com.eollse.activity.MainActivity;
import com.eollse.activity.zwfw.zcxx.ZcxxActivity;
import com.eollse.adapter.ZwfwAdapter;
import com.eollse.app.MyApplication;
import com.eollse.entity.Zwfw;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * 政务服务(二级页面)
 */
public class ZwfwActivity extends BaseActivity {

    @BindView(R.id.tv_backHome)
    TextView tvBackHome;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.gv_zwfw)
    GridView gvZwfw;

    private List<Zwfw> zwfwList = new ArrayList<>();
    private ZwfwAdapter zwfwAdapter;
    private int img[];
    private String title[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zwfw);
        ButterKnife.bind(this);
        tvTitle.setText("政务服务");
        img = new int[]{R.drawable.zwfw_zcxx, R.drawable.zwfw_sqtj, R.drawable.zwfw_jgcx, R.drawable.zwfw_jzbf,
                R.drawable.zwfw_yybs, R.drawable.zwfw_dqfc, R.drawable.zwfw_qtfw, R.drawable.zwfw_wggl,
                R.drawable.zwfw_bszn, R.drawable.zwfw_qgpt, R.drawable.zwfw_pasq, R.drawable.zwfw_sqgk};
        title = new String[]{"政策信息", "诉求提交", "结果查询", "精准帮扶",
                "预约办事", "党群风采", "群团服务", "网格管理",
                "办事指南", "群工平台", "平安社区", "社区概况",};
        setListeners();
        getData();
        setAdapter();
    }

    private void setAdapter() {
        zwfwAdapter = new ZwfwAdapter(getApplicationContext(), zwfwList);
        gvZwfw.setAdapter(zwfwAdapter);
    }

    private void getData() {
        for (int i = 0; i < 12; i++) {
            Zwfw zwfw = new Zwfw();
            zwfw.setIconId(img[i]);
            zwfw.setTitle(title[i]);
            zwfwList.add(zwfw);
        }
    }

    private void setListeners() {
        //回首页
        tvBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ZwfwActivity.this, MainActivity.class);
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

        gvZwfw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                if (i == 0) {
                    //跳转到政策信息
                    intent.setClass(ZwfwActivity.this, ZcxxActivity.class);
                } else if (i == 1) {
                    //跳转到诉求提交
                    intent.setClass(ZwfwActivity.this, SqtjActivity.class);
                } else if (i == 2) {
                    //跳转到结果查询
                    intent.setClass(ZwfwActivity.this, JgcxActivity.class);

                } else if (i == 3) {
                    //跳转到精准帮扶
                    intent.setClass(ZwfwActivity.this, JzbfActivity.class);

                } else if (i == 4) {
                    //跳转到预约办事
                    intent.setClass(ZwfwActivity.this, YybsActivity.class);
                } else if (i == 5) {
                    //跳转到党群风采
                    intent.setClass(ZwfwActivity.this, DqfcActivity.class);
                } else if (i == 6) {
                    //跳转到群团服务
                    intent.setClass(ZwfwActivity.this, QtfwActivity.class);
                } else if (i == 7) {
                    //跳转到网格管理
                    intent.setClass(ZwfwActivity.this, WgglActivity.class);
                } else if (i == 8) {
                    //跳转到办事指南
                    intent.setClass(ZwfwActivity.this, BsznActivity.class);
                } else if (i == 9) {
                    //跳转到群工平台
                    intent.setClass(ZwfwActivity.this, QgptActivity.class);
                } else if (i == 10) {
                    //跳转到平安社区
                    intent.setClass(ZwfwActivity.this, PasqActivity.class);
                } else if (i == 11) {
                    //跳转到社区概况
                    intent.setClass(ZwfwActivity.this, SqgkActivity.class);
                }
                startActivity(intent);
            }
        });
    }


}
