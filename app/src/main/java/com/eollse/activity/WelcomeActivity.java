package com.eollse.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.eollse.R;
import com.eollse.utils.SharedPreUtil;

public class WelcomeActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        handler.sendEmptyMessageDelayed(0,2000);
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    if("".equals(SharedPreUtil.getValue(WelcomeActivity.this, "userinfo", "Key", ""))) {

                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    } else {

                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }

                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
