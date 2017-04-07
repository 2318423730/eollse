package com.eollse.activity.zwfw;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.activity.BaseActivity;
import com.eollse.activity.MainActivity;
import com.eollse.activity.zwfw.pasq.LdrkActivity;
import com.eollse.activity.zwfw.pasq.SqjkActivity;
import com.eollse.adapter.pasq.PasqLeftAdapter;
import com.eollse.app.MyApplication;
import com.eollse.utils.MyLeftLinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 平安社区
 */
public class PasqActivity extends BaseActivity {


    @BindView(R.id.ll_myLeftLinearLayout)
    MyLeftLinearLayout llMyLeftLinearLayout;
    @BindView(R.id.tv_backHome)
    TextView tvBackHome;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.lv_info)
    ListView lvInfo;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.iv_img)
    ImageView ivImg;
    @BindView(R.id.ll_sqjk)
    LinearLayout llSqjk;
    @BindView(R.id.ll_ldrkgl)
    LinearLayout llLdrkgl;
    @BindView(R.id.ll_lwbj)
    LinearLayout llLwbj;
    @BindView(R.id.lv_right_info)
    ListView lvRightInfo;

    private SimpleAdapter simpleAdapter;
    private PasqLeftAdapter pasqLeftAdapter;
    private List<String> infoList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasq);
        ButterKnife.bind(this);


        tvTitle.setText("平安社区");
        setListeners();

//        WebSettings settings = webView.getSettings();
//        settings.setBuiltInZoomControls(true);
//        settings.setSupportZoom(false);
//        settings.setJavaScriptEnabled(true);
//        settings.setDisplayZoomControls(false);
//        webView.setBackgroundColor(0);
//        //webView.setBackgroundResource(R.drawable.shape_info_background);
//        //webView.loadUrl("http://www.baidu.com");
//        String url="http://oa.ybqtw.org.cn/api/Html/ApiResoShow.aspx?TVInfoId=19&ResoId=29&Key=21218CCA77804D2BA1922C33E0151105&typeVer=";
//        webView.loadUrl("http://oa.ybqtw.org.cn/api/Html/ApiResoShow.aspx?TVInfoId="+ SharedPreUtil.getValue(this, "userinfo", "TVInfoId", "")+"&Key="+SharedPreUtil.getValue(this, "userinfo", "Key", "")+
//                "&ResoId=29");
//        webView.setWebViewClient(new WebViewClient(){
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                Log.e("MyTAG",url);
//                view.loadUrl(url);
//                return true;
//            }
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
//
//                super.onPageFinished(view, url);
//            }
//        });
        List<HashMap<String,Object>> data = new ArrayList<HashMap<String,Object>>();
        HashMap<String,Object> mapName = new HashMap<String,Object>();
        mapName.put("info","姓名");
        data.add(mapName);

        HashMap<String,Object> mapPhone = new HashMap<String,Object>();
        mapPhone.put("info","电话");
        data.add(mapPhone);

        HashMap<String,Object> mapJwsPhone = new HashMap<String,Object>();
        mapJwsPhone.put("info","警务室电话");
        data.add(mapJwsPhone);

        HashMap<String,Object> mapBjPhone = new HashMap<String,Object>();
        mapBjPhone.put("info","报警电话");
        data.add(mapBjPhone);
        simpleAdapter=new SimpleAdapter(PasqActivity.this,data,R.layout.item_pasq_right_info,new String[]{"info"},new int[]{R.id.tv_info});
        lvRightInfo.setAdapter(simpleAdapter);


        infoList.add("一季度治安情况通报");
        infoList.add("三分钟教你如何防盗防火防诈骗");
        infoList.add("关于近期社区盗窃频发的文件");
        pasqLeftAdapter=new PasqLeftAdapter(PasqActivity.this,infoList);
        lvInfo.setAdapter(pasqLeftAdapter);
    }

    private void setListeners() {
        llMyLeftLinearLayout.setBackZwfwActivity(this);
    }


    @OnClick({R.id.tv_backHome, R.id.tv_back, R.id.ll_sqjk, R.id.ll_ldrkgl, R.id.ll_lwbj})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.tv_backHome:
                intent.setClass(PasqActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                MyApplication.clearAllActivitiesWithOutMainActivity();
                break;
            case R.id.tv_back:
                finish();
                break;
            case R.id.ll_sqjk:
                intent.setClass(PasqActivity.this, SqjkActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_ldrkgl:
                intent.setClass(PasqActivity.this, LdrkActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_lwbj:

                break;
        }
    }
}
