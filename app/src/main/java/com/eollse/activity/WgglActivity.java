package com.eollse.activity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.adapter.WgglAdapter;
import com.eollse.entity.Wggl;
import com.eollse.utils.SharedPreUtil;

import java.util.ArrayList;
import java.util.List;

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

    private List<Wggl> wgglList = new ArrayList<>();
    private WgglAdapter wgglAdapter;
    private int img[];
    private String title[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wggl);
        ButterKnife.bind(this);

        img = new int[]{R.drawable.wggl_ltjd, R.drawable.wggl_wgryxx, R.drawable.wggl_tjfx,
                R.drawable.wggl_gzgl, R.drawable.wggl_rkgl, R.drawable.wggl_jcsj};
        title = new String[]{"龙塔街道", "网格人员信息", "统计分析",
                "工作管理", "人口管理", "基础数据"};


        tvTitle.setText("网格管理");
        String address = SharedPreUtil.getValue(getApplicationContext(), "userinfo", "Address", "");
        String realName = SharedPreUtil.getValue(getApplicationContext(), "userinfo", "RealName", "");
        String mobile = SharedPreUtil.getValue(getApplicationContext(), "userinfo", "Mobile", "");
        tvAddress.setText("地址:" + address);
        tvRealName.setText("网格长:" + realName);
        tvMobile.setText("联系电话:" + mobile);

        getData();
        setAdapter();
    }

    private void setAdapter() {
        wgglAdapter = new WgglAdapter(getApplicationContext(), wgglList);
        gvWggl.setAdapter(wgglAdapter);
    }

    private void getData() {
        for (int i = 0; i < img.length - 1; i++) {
            Wggl wggl = new Wggl();
            wggl.setIconId(img[i]);
            wggl.setTitle(title[i]);
            wgglList.add(wggl);
        }
    }
}
