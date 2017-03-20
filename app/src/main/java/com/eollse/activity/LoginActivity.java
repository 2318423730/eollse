package com.eollse.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.app.MyApplication;
import com.eollse.utils.Constants;
import com.eollse.utils.HttpCallBack;
import com.eollse.utils.MyToast;
import com.eollse.utils.SharedPreUtil;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 登录
 */
public class LoginActivity extends BaseActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @BindView(R.id.et_userName)
    EditText etUserName;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.ll_login)
    LinearLayout llLogin;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constants.HANDLER_LOGIN_SUCCESS:
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        initData();
        llLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("".equals(etUserName.getText().toString())) {
                    MyToast.showToast(getApplicationContext(), "用户名不能为空!");
                    return;
                } else if ("".equals(etPassword.getText().toString())) {
                    MyToast.showToast(getApplicationContext(), "密码不能为空!");
                    return;
                }
                Login();
            }
        });
    }

    private void Login() {
//        Map<String,String> params=new HashMap<>();
//        params.put("method", "login");
//        params.put("username",etUserName.getText().toString());
//        params.put("pwd",etPassword.getText().toString());

        String url = Constants.BASE_URL + "method=login&" + "username=" + etUserName.getText().toString() + "&pwd=" + etPassword.getText().toString();
        MyApplication.okHttpUtil.get(url, new HttpCallBack() {
            @Override
            public void OnSuccess(String jsonStr) {
                Log.e("MyTAG", "" + jsonStr);

                try {
                    JSONObject object = new JSONObject(jsonStr);
                    String a = object.getString("Status");
                    Log.e("MyTAG", "a=" + a);
                    if ("0".equals(a)) {

                    } else if ("1".equals(a)) {
                        editor.putString("userName", etUserName.getText().toString());
                        editor.putString("Key", object.getString("Key"));
                        editor.putString("TVInfoId", object.getString("TVInfoId"));
                        editor.putString("DeptId", object.getString("DeptId"));
                        editor.putString("Address", object.getString("Address"));
                        editor.putString("UserId", object.getString("UserId"));
                        editor.putString("RealName", object.getString("RealName"));
                        editor.putString("Mobile", object.getString("Mobile"));
                        editor.putString("JobTel", object.getString("JobTel"));
                        editor.commit();

                        handler.sendEmptyMessage(Constants.HANDLER_LOGIN_SUCCESS);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void OnError(String jsonStr) {

            }
        });
    }

    private void initData() {
        sharedPreferences = SharedPreUtil.getSharedPreferences(LoginActivity.this, "userinfo");
        editor = SharedPreUtil.getEditor(LoginActivity.this, "userinfo");
        if (!SharedPreUtil.getValue(this, "userinfo", "userName", "").equals("")) {
            etUserName.setText(SharedPreUtil.getValue(this, "userinfo", "userName", ""));
        } else {
            etUserName.setText("");
        }
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
