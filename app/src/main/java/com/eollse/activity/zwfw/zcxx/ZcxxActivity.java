package com.eollse.activity.zwfw.zcxx;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.eollse.R;
import com.eollse.activity.BaseActivity;
import com.eollse.activity.MainActivity;
import com.eollse.adapter.HorizontalListViewAdapter;
import com.eollse.adapter.ZcxxNewsAdapter;
import com.eollse.app.MyApplication;
import com.eollse.entity.Dept;
import com.eollse.entity.Zcxx;
import com.eollse.ui.MyHorizontalListView;
import com.eollse.utils.Constants;
import com.eollse.utils.HttpCallBack;
import com.eollse.utils.MyToast;
import com.eollse.utils.SharedPreUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * 政策信息
 */
public class ZcxxActivity extends BaseActivity {


    @BindView(R.id.tv_backHome)
    TextView tvBackHome;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.myHorizontalListView)
    MyHorizontalListView myHorizontalListView;

    @BindView(R.id.materialRefreshLayout)
    MaterialRefreshLayout materialRefreshLayout;
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

    List<Dept.DataBean> tab;
    @BindView(R.id.lv_news)
    ListView lvNews;
    /**
     * 新闻集合
     */
    private List<Zcxx.DataBean> newsList = new ArrayList<>();
    private ZcxxNewsAdapter zcxxNewsAdapter;

    private int page = 1;
    private String Deptid = "";
    private int countNum;//总共条数
    private int totalPage;//总共页

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constants.HANDLER_DEPT_RECEIVED:
                    setAdapter();
                    break;
                case Constants.HANDLER_ZCXX_RECEIVED:

                    setPageInfo();
                    setNewsAdapter();

                    break;
                case Constants.HANDLER_NET_ERROR:
                    MyToast.showToast(getApplicationContext(), "网络不给力");
                    //Log.e("MyTAG","网络不给力");
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zcxx);
        ButterKnife.bind(this);
        tvTitle.setText("政策信息");
        //pullRefreshList.setMode(PullToRefreshBase.Mode.BOTH);


        setTopMenu();
        //设置监听器
        setListeners();
        getIndexData(Deptid);//默认进入时的信息

    }


    private Zcxx zcxx;

    private void getIndexData(String Deptid) {
        //url = http://oa.ybqtw.org.cn/api/APP1.0.aspx?&TVInfoId=19&method=IndexNews&PageSizepage=10&Page=1&ClassId=1&Deptid=&Key=21218CCA77804D2BA1922C33E0151105

        String url = Constants.BASE_URL + "?&TVInfoId=19&method=IndexNews&PageSize=10&Page=" + page + "&ClassId=1&Deptid=" + Deptid + "&Key=21218CCA77804D2BA1922C33E0151105";
        MyApplication.okHttpUtil.get(url, new HttpCallBack() {
            @Override
            public void OnSuccess(String jsonStr) {
                zcxx = JSON.parseObject(jsonStr, Zcxx.class);
                if (newsList == null) {
                    newsList = new ArrayList<Zcxx.DataBean>();
                }
                newsList.clear();
                newsList.addAll(zcxx.getData());

                countNum = Integer.parseInt(zcxx.getCountNum());
                totalPage = countNum / 10;
                if (countNum % 10 != 0) {
                    totalPage += 1;
                }

                handler.sendEmptyMessage(Constants.HANDLER_ZCXX_RECEIVED);

            }

            @Override
            public void OnError(String jsonStr) {
                handler.sendEmptyMessage(Constants.HANDLER_NET_ERROR);//没有网络
                materialRefreshLayout.finishRefresh();//用于关闭刷新
            }
        });
    }

    private void setTopMenu() {
//        // 添加TextView控件
//        // 参数设置
//        LinearLayout.LayoutParams menuLinerLayoutParames =
//                new LinearLayout.LayoutParams(
//                        LinearLayout.LayoutParams.WRAP_CONTENT,
//                        LinearLayout.LayoutParams.WRAP_CONTENT,
//                        1);
//        for (int i = 1; i <=33; i++) {
//
//            RadioButton tempButton = new RadioButton(this);
//            //tempButton.setBackgroundResource(R.drawable.selector_top_title_background);// 设置RadioButton的背景图片
//            tempButton.setButtonDrawable(android.R.color.transparent); // 设置按钮的样式
//            tempButton.setPadding(20, 0, 20, 0);// 设置文字距离按钮四周的距离
//            tempButton.setText("标题 " + i);
//            tempButton.setTextColor(this.getResources().getColorStateList(R.color.selector_top_title_text));
//            if (i==0){
//                tempButton.setChecked(true);
//            }
//            rgTop.addView(tempButton, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
//        }

        tab = new ArrayList();

        //TVInfoId=19&method=DeptList&Key=21218CCA77804D2BA1922C33E0151105
        Map<String, String> map = new HashMap<>();
        map.put("TVInfoId", SharedPreUtil.getValue(this, "userinfo", "TVInfoId", ""));
        map.put("method", "DeptNewsTab");
        map.put("Key", SharedPreUtil.getValue(this, "userinfo", "Key", ""));
        MyApplication.okHttpUtil.post(Constants.BASE_URL, map, new HttpCallBack() {
            @Override
            public void OnSuccess(String jsonStr) {
                tab.clear();
                Dept dept = JSON.parseObject(jsonStr, Dept.class);

                tab = dept.getData();
                Dept.DataBean dataBean = new Dept.DataBean();
                dataBean.setDeptName("所有部门");
                tab.add(0, dataBean);
                handler.sendEmptyMessage(Constants.HANDLER_DEPT_RECEIVED);
            }

            @Override
            public void OnError(String jsonStr) {
                handler.sendEmptyMessage(Constants.HANDLER_NET_ERROR);

            }
        });

    }

    private HorizontalListViewAdapter horizontalListViewAdapter;

    private void setListeners() {
        //回首页
        tvBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ZcxxActivity.this, MainActivity.class);
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

//        rgTop.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
//
//            }
//        });
        ivRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //int a = myHorizontalListView.getFirstVisiblePosition();
                //int b = myHorizontalListView.getLastVisiblePosition();
                //Log.e("MyTAG", "a=" + a + "   b=" + b + "   a-b=" + (b - a));
                //myHorizontalListView.setSelection(b - 1);
                myHorizontalListView.scrollTo(1000);


            }
        });
        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myHorizontalListView.scrollTo(0);
            }
        });

        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {

                newsList.clear();
                getIndexData(Deptid);


            }
        });

        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (page < totalPage) {
                    page++;
                    newsList.clear();
                    if (zcxxNewsAdapter == null) {
                        zcxxNewsAdapter = new ZcxxNewsAdapter(getApplicationContext(), newsList);
                    }
                    zcxxNewsAdapter.notifyDataSetChanged();
                    getIndexData(Deptid);
                }
                setPageInfo();
            }
        });
        tvPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (page > 1) {
                    page--;
                    newsList.clear();
                    if (zcxxNewsAdapter == null) {
                        zcxxNewsAdapter = new ZcxxNewsAdapter(getApplicationContext(), newsList);
                    }
                    zcxxNewsAdapter.notifyDataSetChanged();
                    getIndexData(Deptid);
                }
                setPageInfo();
            }
        });

        lvNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {


                String url = Constants.BASE_NEWS_URL+"TVInfoId="+SharedPreUtil.getValue(ZcxxActivity.this, "userinfo", "TVInfoId", "")
                        +"&Key="+SharedPreUtil.getValue(ZcxxActivity.this, "userinfo", "Key", "")+"&id="+newsList.get(position).getNewsId();
                Log.e("MyTAG", "url=" + url);
                Intent intent=new Intent(ZcxxActivity.this,ZcxxNewsDetailActivity.class);
                intent.putExtra("url",url);
                intent.putExtra("from","zcxxNews");
                startActivity(intent);
            }
        });
    }


    private void setAdapter() {

        horizontalListViewAdapter = new HorizontalListViewAdapter(getApplicationContext(), tab);
        myHorizontalListView.setAdapter(horizontalListViewAdapter);
        horizontalListViewAdapter.setSelectIndex(0);
        horizontalListViewAdapter.notifyDataSetChanged();
        myHorizontalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Deptid = "" + tab.get(position).getDeptid();
                if (position == 0) {
                    Deptid = "";
                }
                materialRefreshLayout.finishRefresh();
                //标题栏选中切换
                horizontalListViewAdapter.setSelectIndex(position);
                horizontalListViewAdapter.notifyDataSetChanged();

                page = 1; // 页数
                newsList.clear();
                if (position == 0) {
                    getIndexData("");
                } else {
                    Deptid = "" + tab.get(position).getDeptid();
                    getIndexData(Deptid);
                }


            }
        });
    }


    private void setNewsAdapter() {
        if (zcxxNewsAdapter == null) {
            zcxxNewsAdapter = new ZcxxNewsAdapter(getApplicationContext(), newsList);
            lvNews.setAdapter(zcxxNewsAdapter);
        } else {
            zcxxNewsAdapter.notifyDataSetChanged();
            // materialRefreshLayout.finishRefresh();
            if (newsList.size() > 0) {
                lvNews.setSelection(0);
            }

        }
        materialRefreshLayout.finishRefresh();
    }

    private void setPageInfo() {
        tvCurrentPage.setText("" + page);
        tvTotalSize.setText("" + countNum);
        tvTotalPage.setText("" + totalPage);
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
