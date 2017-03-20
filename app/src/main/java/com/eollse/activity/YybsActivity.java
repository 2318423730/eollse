package com.eollse.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.eollse.R;
import com.eollse.adapter.JgcxAdapter;
import com.eollse.app.MyApplication;
import com.eollse.entity.Jgcx;
import com.eollse.utils.Constants;
import com.eollse.utils.HttpCallBack;
import com.eollse.utils.SharedPreUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 预约办事
 */
public class YybsActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.tv_backHome)
    TextView tvBackHome;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.et_userName)
    EditText etUserName;
    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.lv_info)
    ListView lvInfo;
    @BindView(R.id.tv_noInfo)
    TextView tvNoInfo;
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

    private String url;

    private String Action;
    private List<Jgcx.DataBean> list;
    private int page = 1;
    private int countNum;//总共条数
    private int totalPage;//总共页
    private String newsUrl;
    private JgcxAdapter adapter;

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
        setContentView(R.layout.activity_jgcx);
        ButterKnife.bind(this);
        tvTitle.setText("预约办事");
        Action = "score";
        getData();
        setListeners();

    }

    private void setListeners() {
        tvPrevious.setOnClickListener(this);
        tvNext.setOnClickListener(this);
        tvSearch.setOnClickListener(this);
        //回首页
        tvBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(YybsActivity.this, MainActivity.class);
                startActivity(intent);

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
        lvInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(YybsActivity.this, JgcxContentActivity.class);

                intent.putExtra("HandleId", "" + list.get(position).getHandleId());//int变成String传值
                intent.putExtra("AuditState", "" + list.get(position).getAuditState());//int变成String传值
                intent.putExtra("Action", Action);//String


                startActivity(intent);
            }
        });
        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                getData();
            }
        });
    }

    private void getData() {
        url = Constants.BASE_URL + "method=HandleList&TVInfoId=" + SharedPreUtil.getValue(this, "userinfo", "TVInfoId", "") + "&key=" +
                SharedPreUtil.getValue(this, "userinfo", "Key", "") + "&Page=" + page + "&PageSize=6&DeptId=" + SharedPreUtil.getValue(this, "userinfo", "DeptId", "") +
                "&UserName=" + etUserName.getText().toString() + "&MobileNo=" + etMobile.getText().toString() + "Action=" + Action;
        Log.e("MyTAG", url);

        MyApplication.okHttpUtil.get(url, new HttpCallBack() {
            @Override
            public void OnSuccess(String jsonStr) {
                Jgcx jgcx = JSON.parseObject(jsonStr, Jgcx.class);
                if (list == null) {
                    list = new ArrayList<Jgcx.DataBean>();
                }
                list.clear();
                list.addAll(jgcx.getData());
                Action = ((String) JSON.parseObject(jsonStr).get("Action"));
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

    private void setPageInfo() {
        tvCurrentPage.setText("" + page);
        tvTotalSize.setText("" + countNum);
        tvTotalPage.setText("" + totalPage);
    }

    private void setAdapter() {
        if (adapter == null) {
            adapter = new JgcxAdapter(YybsActivity.this, list);
            lvInfo.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();

            if (list.size() > 0) {
                lvInfo.setSelection(0);
            }

        }
        materialRefreshLayout.finishRefresh();
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
            case R.id.tv_search:
                list.clear();
                getData();
                break;
        }
    }
    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
