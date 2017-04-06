package com.eollse.activity.zwfw;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.activity.BaseActivity;
import com.eollse.activity.MainActivity;
import com.eollse.activity.zwfw.jzbf.BfdxFragment;
import com.eollse.activity.zwfw.jzbf.BfhdFragment;
import com.eollse.activity.zwfw.jzbf.BfllFragment;
import com.eollse.activity.zwfw.jzbf.BfzcFragment;
import com.eollse.app.MyApplication;
import com.eollse.ui.JzbfDialog;
import com.eollse.utils.MyLeftLinearLayout;

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
//    @BindView(R.id.tv_login)
//    TextView tvLogin;

    @BindView(R.id.ll_content)
    LinearLayout llContent;


    private List<Fragment> fragmentList;
    private int currentWillShowFragment;
    private int currentShowFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jzbf);
        ButterKnife.bind(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

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
        fragmentList.add(new BfzcFragment());
        fragmentList.add(new BfllFragment());
        fragmentList.add(new BfdxFragment());
        fragmentList.add(new BfhdFragment());
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
                    // 动作4:提交
                    transaction.commit();
                    currentShowFragment = currentWillShowFragment;
                }
            }
        });



    }


    @OnClick({R.id.tv_backHome, R.id.tv_back})
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
//            case R.id.tv_login://展开登录窗口
//                createDialog();
//                break;

        }
    }

//    private void createDialog() {
//
//        dialog=new JzbfDialog(this);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setCancelable(false);
//        dialog.show();
//        InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
//        inputMethodManager.hideSoftInputFromWindow( this.getWindow().getDecorView().getWindowToken(), 0);
//    }
//
//    private JzbfDialog dialog;
}
