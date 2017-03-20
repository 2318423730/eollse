package com.eollse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.app.MyApplication;
import com.eollse.utils.SharedPreUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 平安社区
 */
public class PasqActivity extends BaseActivity {

    @BindView(R.id.tv_backHome)
    TextView tvBackHome;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.webView)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasq);
        ButterKnife.bind(this);

        tvTitle.setText("平安社区");
        setListeners();

        WebSettings settings = webView.getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(false);
        settings.setJavaScriptEnabled(true);
        settings.setDisplayZoomControls(false);
        webView.setBackgroundColor(0);
        //webView.setBackgroundResource(R.drawable.shape_info_background);
        //webView.loadUrl("http://www.baidu.com");
        String url="http://oa.ybqtw.org.cn/api/Html/ApiResoShow.aspx?TVInfoId=19&ResoId=29&Key=21218CCA77804D2BA1922C33E0151105&typeVer=";
        webView.loadUrl("http://oa.ybqtw.org.cn/api/Html/ApiResoShow.aspx?TVInfoId="+ SharedPreUtil.getValue(this, "userinfo", "TVInfoId", "")+"&Key="+SharedPreUtil.getValue(this, "userinfo", "Key", "")+
                "&ResoId=29");
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.e("MyTAG",url);
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {

                super.onPageFinished(view, url);
            }
        });
    }

    private void setListeners() {
        //回首页
        tvBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PasqActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                MyApplication.clearAllActivitiesWithOutMainActivity();
            }
        });
        //返回
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(webView.canGoBack()) {
                    webView.goBack();
                }else {
                    finish();
                }
            }
        });
    }


}
