package com.eollse.activity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.eollse.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 社会服务(二级页面)
 */
public class ShfuActivity extends BaseActivity {

    @BindView(R.id.tv_backHome)
    TextView tvBackHome;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.rb_wyfw)
    RadioButton rbWyfw;
    @BindView(R.id.rb_bmfw)
    RadioButton rbBmfw;
    @BindView(R.id.rg_top)
    RadioGroup rgTop;
    @BindView(R.id.gv_wyfw)
    GridView gvWyfw;
    @BindView(R.id.gv_bmfw)
    GridView gvBmfw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shfu);
        ButterKnife.bind(this);
    }
}
