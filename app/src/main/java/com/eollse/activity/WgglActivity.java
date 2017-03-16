package com.eollse.activity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import com.eollse.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 网格管理
 */
public class WgglActivity extends BaseActivity {

    @BindView(R.id.tv_backHome)
    TextView tvBackHome;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_Address)
    TextView tvAddress;
    @BindView(R.id.tv_RealName)
    TextView tvRealName;
    @BindView(R.id.tv_Mobile)
    TextView tvMobile;
    @BindView(R.id.gv_wggl)
    GridView gvWggl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wggl);
        ButterKnife.bind(this);

        tvTitle.setText("网格管理");
    }
}
