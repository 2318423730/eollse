package com.eollse.activity.zwfw;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.activity.BaseActivity;
import com.eollse.activity.MainActivity;
import com.eollse.activity.zwfw.jzbf.BfdxFregment;
import com.eollse.activity.zwfw.jzbf.BfhdFregment;
import com.eollse.activity.zwfw.jzbf.BfllFregment;
import com.eollse.activity.zwfw.jzbf.BfzcFregment;
import com.eollse.app.MyApplication;
import com.eollse.utils.MyLeftLinearLayout;
import com.eollse.utils.MyToast;
import com.eollse.utils.SharedPreUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 精准帮扶
 */
public class JzbfActivity extends BaseActivity {

    @BindView(R.id.ll_myLeftLinearLayout)
    MyLeftLinearLayout llMyLeftLinearLayout;
    @BindView(R.id.tv_backHome)
    TextView tvBackHome;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.rb_bfzc)
    RadioButton rbBfzc;
    @BindView(R.id.rb_bfll)
    RadioButton rbBfll;
    @BindView(R.id.rb_bfdx)
    RadioButton rbBfdx;
    @BindView(R.id.rb_bfhd)
    RadioButton rbBfhd;
    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;
    @BindView(R.id.rg_title)
    RadioGroup rgTitle;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.rl_login)
    RelativeLayout rlLogin;
    @BindView(R.id.et_userName)
    EditText etUserName;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.iv_cancle)
    ImageView ivCancle;


    private List<Fragment> fragmentList;
    private int currentWillShowFragment;
    private int currentShowFragment;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private boolean check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jzbf);
        ButterKnife.bind(this);

        tvTitle.setText("精准帮扶");
        setListeners();

        setFragmets();


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, fragmentList.get(currentShowFragment));
        fragmentTransaction.show(fragmentList.get(currentShowFragment));
        fragmentTransaction.commit();

    }

    private void setFragmets() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new BfzcFregment());
        fragmentList.add(new BfllFregment());
        fragmentList.add(new BfdxFregment());
        fragmentList.add(new BfhdFregment());
    }

    private void setListeners() {
        llMyLeftLinearLayout.setBackZwfwActivity(this);
        //标题切换
        rgTitle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.rb_bfzc:
                        currentWillShowFragment = 0;
                        break;
                    case R.id.rb_bfll:
                        currentWillShowFragment = 1;
                        break;
                    case R.id.rb_bfdx:
                        currentWillShowFragment = 2;
                        break;
                    case R.id.rb_bfhd:
                        currentWillShowFragment = 3;
                        break;
                }
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                if (currentShowFragment != currentWillShowFragment) {
                    // 动作1:隐藏以前的fragment
                    transaction.hide(fragmentList.get(currentShowFragment));
                    // 动作2:添加新的fragment
                    Fragment f = fragmentList.get(currentWillShowFragment);
                    if (f.isAdded() == false) {
                        transaction.add(R.id.fragment_container, f);
                    }
                    // 动作3:显示新的fragment
                    transaction.show(f);

                    transaction.commit();
                    currentShowFragment = currentWillShowFragment;
                }
            }
        });


        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                check = isChecked;
            }
        });
        ivCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etUserName.setText("");
                etPassword.setText("");
                rlLogin.setVisibility(View.GONE);
            }
        });
    }

    private void showState() {
        sharedPreferences = SharedPreUtil.getSharedPreferences(JzbfActivity.this, "jzbf_userinfo");
        editor = SharedPreUtil.getEditor(JzbfActivity.this, "jzbf_userinfo");
        //是否勾选记住账号
        check = SharedPreUtil.getValue(this, "jzbf_userinfo", "check", false);
        if (check) {
            checkbox.setChecked(true);
        } else {
            checkbox.setChecked(false);
        }

        if (!SharedPreUtil.getValue(this, "jzbf_userinfo", "userName", "").equals("")) {
            userName = SharedPreUtil.getValue(this, "jzbf_userinfo", "userName", "");
            etUserName.setText(userName);
        }
    }

    private String userName;
    private String password;

    private void login() {


        if ("".equals(etUserName.getText().toString())) {
            MyToast.showToast(getApplicationContext(), "账号不能为空");
            return;
        }
        if ("".equals(etPassword.getText().toString())) {
            MyToast.showToast(getApplicationContext(), "密码不能为空");
            return;
        }


        userName = etUserName.getText().toString();
        if (check) {
            editor.putString("userName", userName);
            editor.putBoolean("check", check);
        } else {
            editor.clear();
        }
        editor.commit();

        MyToast.showToast(getApplicationContext(), "成功");
        etUserName.setText("");
        etPassword.setText("");
        rlLogin.setVisibility(View.GONE);
    }

    @OnClick({R.id.tv_backHome, R.id.tv_back, R.id.tv_login, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_backHome:
                Intent intent = new Intent(JzbfActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                MyApplication.clearAllActivitiesWithOutMainActivity();
                break;
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_login://展开登录窗口
                rlLogin.setVisibility(View.VISIBLE);
                showState();
                break;
            case R.id.btn_login://登录
                login();
                break;
        }
    }


}
