package com.eollse.activity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.eollse.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 社区概况
 */
public class SqgkActivity extends BaseActivity {

    @BindView(R.id.tv_backHome)
    TextView tvBackHome;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.lv_sqgk)
    ListView lvSqgk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqgk);
        ButterKnife.bind(this);
        tvTitle.setText("社区概况");

    }
}
