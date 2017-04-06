package com.eollse.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import com.eollse.R;
import com.eollse.activity.zwfw.JzbfActivity;
import com.eollse.utils.MyToast;
import com.eollse.utils.SharedPreUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/1/0001.
 */

public class JzbfDialog extends Dialog {

//    private ImageView ivCancle;
//
//    private EditText etUserName;
//
//    private EditText etPassword;
//
//    private CheckBox checkbox;
//
//    private Button btnLogin;
//
//    private Context context;
//
//    private SharedPreferences sharedPreferences;
//    private SharedPreferences.Editor editor;
//    private boolean check;


    public JzbfDialog(Context context) {
        super(context);
        //this.context = context;
    }

    public JzbfDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected JzbfDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

//    @Override
//    public void show() {
//        super.show();
//        Window window = this.getWindow();
//        //window.setBackgroundDrawable(new ColorDrawable(0xffffffff));
//        View view = View.inflate(context, R.layout.dialog_jzbf, null);
//
//
//        window.setContentView(view);
//        setViews(view);
//        setListeners();
//        showState();
//        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//
//    }
//
//    private void setViews(View view) {
//        ivCancle= (ImageView) view.findViewById(R.id.iv_cancle);
//        etUserName= (EditText) view.findViewById(R.id.et_userName);
//        etPassword= (EditText) view.findViewById(R.id.et_password);
//        btnLogin= (Button) view.findViewById(R.id.btn_login);
//        checkbox= (CheckBox) view.findViewById(R.id.checkbox);
//
//    }
//
//    private void setListeners() {
//        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                check = isChecked;
//            }
//        });
//
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                login();
//            }
//        });
//
//        ivCancle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dismiss();
//            }
//        });
//    }
//
//
//    private void showState() {
//        sharedPreferences = SharedPreUtil.getSharedPreferences(context, "jzbf_userinfo");
//        editor = SharedPreUtil.getEditor(context, "jzbf_userinfo");
//        //是否勾选记住账号
//        check = SharedPreUtil.getValue(context, "jzbf_userinfo", "check", false);
//        if (check) {
//            checkbox.setChecked(true);
//        } else {
//            checkbox.setChecked(false);
//        }
//
//        if (!SharedPreUtil.getValue(context, "jzbf_userinfo", "userName", "").equals("")) {
//            userName = SharedPreUtil.getValue(context, "jzbf_userinfo", "userName", "");
//            etUserName.setText(userName);
//        }
//    }
//
//
//    private String userName;
//    private String password;
//
//    private void login() {
//
//
//        if ("".equals(etUserName.getText().toString())) {
//            MyToast.showToast(context, "账号不能为空");
//            return;
//        }
//        if ("".equals(etPassword.getText().toString())) {
//            MyToast.showToast(context, "密码不能为空");
//            return;
//        }
//
//
//        userName = etUserName.getText().toString();
//        if (check) {
//            editor.putString("userName", userName);
//            editor.putBoolean("check", check);
//        } else {
//            editor.clear();
//        }
//        editor.commit();
//
//        MyToast.showToast(context, "成功");
//        etUserName.setText("");
//        etPassword.setText("");
//        dismiss();
//
//    }
}
