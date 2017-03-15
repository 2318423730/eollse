package com.eollse.activity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import com.eollse.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 群团服务
 */
public class QtfwActivity extends BaseActivity {

    @BindView(R.id.tv_backHome)
    TextView tvBackHome;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.gv_qtfw)
    GridView gvQtfw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qtfw);
        ButterKnife.bind(this);

    }
}
