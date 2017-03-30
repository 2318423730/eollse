package com.eollse.activity.zwfw;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.eollse.R;
import com.eollse.activity.BaseActivity;
import com.eollse.activity.MainActivity;
import com.eollse.activity.zwfw.zcxx.ZcxxNewsDetailActivity;
import com.eollse.adapter.MainNewsAdapter;
import com.eollse.app.MyApplication;
import com.eollse.entity.MainNew;
import com.eollse.utils.Constants;
import com.eollse.utils.HttpCallBack;
import com.eollse.utils.MyLeftLinearLayout;
import com.eollse.utils.SharedPreUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 社区概况
 */
public class SqgkActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv_backHome)
    TextView tvBackHome;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.lv_sqgk)
    ListView lvSqgk;
    @BindView(R.id.tv_previous)
    TextView tvPrevious;
    @BindView(R.id.tv_currentPage)
    TextView tvCurrentPage;
    @BindView(R.id.tv_totalPage)
    TextView tvTotalPage;
    @BindView(R.id.tv_totalSize)
    TextView tvTotalSize;
    @BindView(R.id.tv_next)
    TextView tvNext;
    @BindView(R.id.materialRefreshLayout)
    MaterialRefreshLayout materialRefreshLayout;
    @BindView(R.id.ll_myLeftLinearLayout)
    MyLeftLinearLayout llMyLeftLinearLayout;
    private String url;
    private int page = 1;
    private int countNum;//总共条数
    private int totalPage;//总共页
    private String newsUrl;

    private List<MainNew.DataBean> list;
    private MainNewsAdapter adapter;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constants.HANDLER_INFO_RECEIVED:
                    setPageInfo();
                    setAdapter();
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqgk);
        ButterKnife.bind(this);
        tvTitle.setText("社区概况");
        getData();
        setListeners();

    }

    private void getData() {
        url = Constants.BASE_URL + "ClassId=3&TVInfoId=" + SharedPreUtil.getValue(this, "userinfo", "TVInfoId", "") +
                "&method=IndexNews&Page=" + page + "&Key=" + SharedPreUtil.getValue(this, "userinfo", "Key", "") + "&PageSize=10&Deptid=2";
        MyApplication.okHttpUtil.get(url, new HttpCallBack() {
            @Override
            public void OnSuccess(String jsonStr) {
                if (list == null) {
                    list = new ArrayList<MainNew.DataBean>();
                }

                MainNew mainNew = JSON.parseObject(jsonStr, MainNew.class);
                list.clear();
                list.addAll(mainNew.getData());
                countNum = Integer.parseInt((String) JSON.parseObject(jsonStr).get("CountNum"));
                newsUrl = (String) JSON.parseObject(jsonStr).get("NewsShowUrl");
                totalPage = countNum / 10;
                if (countNum % 10 != 0) {
                    totalPage += 1;
                }
                handler.sendEmptyMessage(Constants.HANDLER_INFO_RECEIVED);

            }

            @Override
            public void OnError(String jsonStr) {

            }
        });
    }

    private void setListeners() {
        llMyLeftLinearLayout.setBackZwfwActivity(this);
        tvPrevious.setOnClickListener(this);
        tvNext.setOnClickListener(this);

        //回首页
        tvBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SqgkActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                MyApplication.clearAllActivitiesWithOutMainActivity();
            }
        });
        //返回
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                getData();
            }
        });

        lvSqgk.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                String url = Constants.BASE_NEWS_URL+"method=newshtml"+"&userid="+SharedPreUtil.getValue(getApplicationContext(), "userinfo", "UserId", "")
//                        +"&Key="+SharedPreUtil.getValue(getApplicationContext(), "userinfo", "Key", "")+"&id="+list.get(position).getNewsId();
//                Log.e("MyTAG", "url=" + url);
//                Intent intent=new Intent(SqgkActivity.this,NewsDetailActivity.class);
//                intent.putExtra("url",url);
//                intent.putExtra("from","sqgkNews");
//                startActivity(intent);
                String url = Constants.BASE_NEWS_URL + "TVInfoId=" + SharedPreUtil.getValue(getApplicationContext(), "userinfo", "TVInfoId", "")
                        + "&Key=" + SharedPreUtil.getValue(getApplicationContext(), "userinfo", "Key", "") + "&id=" + list.get(position).getNewsId();
                Log.e("MyTAG", "url=" + url);
                Intent intent = new Intent(SqgkActivity.this, ZcxxNewsDetailActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("from", "sqgkNews");
                startActivity(intent);

            }
        });
    }

    private void setPageInfo() {
        tvCurrentPage.setText("" + page);
        tvTotalSize.setText("" + countNum);
        tvTotalPage.setText("" + totalPage);
    }

    private void setAdapter() {
        if (adapter == null) {//第一次加载
            adapter = new MainNewsAdapter(getApplicationContext(), list);
            lvSqgk.setAdapter(adapter);
        } else {//刷新
            adapter.notifyDataSetChanged();

            if (list.size() > 0) {
                lvSqgk.setSelection(0);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_previous:
                if (page > 1) {
                    page--;
                    list.clear();
                    getData();
                }
                break;
            case R.id.tv_next:
                if (page < totalPage) {
                    page++;
                    list.clear();
                    getData();
                }

        }
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }


}
