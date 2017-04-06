package com.eollse.activity.zwfw.pasq;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.activity.BaseActivity;
import com.eollse.activity.MainActivity;
import com.eollse.activity.zwfw.zcxx.ZcxxActivity;
import com.eollse.app.MyApplication;
import com.eollse.utils.MyLeftLinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 流动人口
 */
public class LdrkActivity extends BaseActivity {

    @BindView(R.id.ll_myLeftLinearLayout)
    MyLeftLinearLayout llMyLeftLinearLayout;
    @BindView(R.id.tv_backHome)
    TextView tvBackHome;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.lv_leftInfo)
    ListView lvLeftInfo;
    @BindView(R.id.et_sqName)
    EditText etSqName;
    @BindView(R.id.et_userName)
    EditText etUserName;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_reset)
    Button btnReset;
    @BindView(R.id.ll_login)
    LinearLayout llLogin;

    private ArrayAdapter<String> arrayAdapter;
    private List<String> monthList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ldrk);
        ButterKnife.bind(this);

        monthList.add("2017-3");
        monthList.add("2017-4");
        monthList.add("2017-5");
        monthList.add("2017-6");
        arrayAdapter=new ArrayAdapter<String>(LdrkActivity.this,android.R.layout.simple_list_item_1,monthList);
        lvLeftInfo.setAdapter(arrayAdapter);
    }

    @OnClick({R.id.tv_backHome, R.id.tv_back, R.id.btn_login, R.id.btn_reset})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_backHome:
                Intent intent = new Intent(LdrkActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                MyApplication.clearAllActivitiesWithOutMainActivity();
                break;
            case R.id.tv_back:
                finish();
                break;
            case R.id.btn_login:
                break;
            case R.id.btn_reset:
                break;
        }
    }
}
