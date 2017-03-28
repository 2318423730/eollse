package com.eollse.activity.zwfw.zcxx;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.activity.BaseActivity;
import com.eollse.activity.MainActivity;
import com.eollse.app.MyApplication;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * 政策信息新闻详情
 */
public class ZcxxNewsDetailActivity extends BaseActivity {
    @BindView(R.id.tv_backHome)
    TextView tvBackHome;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.webView)
    WebView webView;
    private String url;
    private String from;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        from=intent.getStringExtra("from");
        if("zcxxNews".equals(from)){
            tvTitle.setText("政策资讯");
        }else if("mainNews".equals(from)){
            tvTitle.setText("标题");
        }else if("sqgkNews".equals(from)){
            tvTitle.setText("社区新闻");
        }

        setListeners();

        WebSettings settings = webView.getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(false);
        settings.setJavaScriptEnabled(true);
        settings.setDisplayZoomControls(false);
        webView.setBackgroundColor(0);


        webView.loadUrl(url);

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
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
                Intent intent = new Intent(ZcxxNewsDetailActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                MyApplication.clearAllActivitiesWithOutMainActivity();
            }
        });
        //返回
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (webView.canGoBack()) {
                    webView.goBack();
                } else {
                    finish();
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack();
                return true;
            } else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }



}
