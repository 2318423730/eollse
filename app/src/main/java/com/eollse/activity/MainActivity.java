package com.eollse.activity;


import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.eollse.R;
import com.eollse.activity.shfw.ShfwActivity;
import com.eollse.activity.zwfw.BsznActivity;
import com.eollse.activity.zwfw.DqfcActivity;
import com.eollse.activity.zwfw.JgcxActivity;
import com.eollse.activity.zwfw.SqtjActivity;
import com.eollse.activity.zwfw.WgglActivity;
import com.eollse.activity.zwfw.ZwfwActivity;
import com.eollse.activity.zwfw.zcxx.ZcxxActivity;
import com.eollse.activity.zwfw.zcxx.ZcxxNewsDetailActivity;

import com.eollse.adapter.MainNewsAdapter;
import com.eollse.app.MyApplication;
import com.eollse.entity.MainNew;
import com.eollse.entity.Video;
import com.eollse.ui.MyPmdTextView;

import com.eollse.ui.MyVitamioVideoView;
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
import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;

/**
 * 首页
 */
public class MainActivity extends BaseActivity implements OnClickListener {


    @BindView(R.id.ll_top1)
    LinearLayout llTop1;


    @BindView(R.id.lv_listview)
    ListView lvListview;
    @BindView(R.id.ll_middle_left)
    LinearLayout llMiddleLeft;

    @BindView(R.id.iv_zwfu)
    ImageView ivZwfu;
    @BindView(R.id.iv_shfw)
    ImageView ivShfw;

    @BindView(R.id.tv_pmd)
    MyPmdTextView tvPmd;
    @BindView(R.id.rl_videoView)
    RelativeLayout rlVideoView;
    @BindView(R.id.tv_loadingText)
    TextView tvLoadingText;
    @BindView(R.id.ll_loading)
    LinearLayout llLoading;
    @BindView(R.id.ll_sqtj)
    LinearLayout llSqtj;
    @BindView(R.id.ll_zcxx)
    LinearLayout llZcxx;
    @BindView(R.id.ll_jgcx)
    LinearLayout llJgcx;
    @BindView(R.id.ll_dqfc)
    LinearLayout llDqfc;
    @BindView(R.id.ll_wyfw)
    LinearLayout llWyfw;
    @BindView(R.id.ll_bszn)
    LinearLayout llBszn;
    @BindView(R.id.materialRefreshLayout)
    MaterialRefreshLayout materialRefreshLayout;
    @BindView(R.id.ll_middle_right)
    LinearLayout llMiddleRight;
    @BindView(R.id.videoView)
    MyVitamioVideoView videoView;

    /**
     * 轮播文字
     */
    private String str = "";
    /**
     * 轮播数据集合
     */
    private List<String> pmdList;

    /**
     * 视频列表集合
     */
    private List<Video.TrailersBean> videosList;
    /**
     * 视频类
     */
    private Video video;

    /**
     * 播放视频的地址
     */
    private String videoUrl;
    /**
     * 视频真实的宽
     */
    private int videoWidth;
    /**
     * 视频真实的高
     */
    private int videoHeight;
    /**
     * 当前播放的视频索引
     */
    private int videoPosition;

    /**
     * 主页新闻的适配器
     */
    private MainNewsAdapter adapter;

    private boolean hasError;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constants.HANDLER_VIDEO_RECEIVEED://获取到视频播放列表
                    //播放视频
                    if (videosList != null && videosList.size() > 0) {
                        playVideo();
                    }

                    break;
                case Constants.HANDLER_VIDEO_RESUME://继续上次播放
                    if (videoView.isPlaying()) {
                        handler.removeMessages(Constants.HANDLER_VIDEO_RESUME);
                        long jindu = MyApplication.lastPlayPosition;
                        videoView.seekTo(jindu);
                    } else {
                        handler.sendEmptyMessage(Constants.HANDLER_VIDEO_RESUME);
                    }
                    break;
                case Constants.HANDLER_MAINNEWS_RECEIVED:
                    //设置新闻适配器
                    setAdapter();
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
        Vitamio.isInitialized(MainActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (!LibsChecker.checkVitamioLibs(this))
            return;

        //设置监听器
        setListeners();
        //设置轮播数据
        setPmdData();
        for (int i = 0; i < pmdList.size(); i++) {
            str += (pmdList.get(i) + "\t\t\t\t\t\t\t\t\t\t");
        }
        tvPmd.setText(str);

        //设置新闻数据
        setNews();


        getVideos();
    }

    /**
     * 设置控件监听
     */
    private void setListeners() {
        //ivSetting.setOnClickListener(this);
        rlVideoView.setOnClickListener(this);
        llSqtj.setOnClickListener(this);
        llZcxx.setOnClickListener(this);
        llJgcx.setOnClickListener(this);
        llDqfc.setOnClickListener(this);
        llWyfw.setOnClickListener(this);
        llBszn.setOnClickListener(this);
        ivZwfu.setOnClickListener(this);
        ivShfw.setOnClickListener(this);
        tvPmd.setOnClickListener(this);
        // ivSearch.setOnClickListener(this);
        //ivAdd.setOnClickListener(this);

        //新闻item监听
        lvListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //Toast.makeText(getApplicationContext(), "点击了第" + position + "条", Toast.LENGTH_SHORT).show();
            }
        });
        //准备好的监听
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //此处设置播放速度为正常速度1
               // mp.setPlaybackSpeed(1.0f);

                videoWidth = mp.getVideoWidth();
                videoHeight = mp.getVideoHeight();
                int mVideoWidth = videoWidth;
                int mVideoHeight = videoHeight;

                //控件宽高
                int width = videoView.getMeasuredWidth();
                int height = videoView.getMeasuredHeight();
                // Log.e("MyTAG","width="+width+"   height="+height);
                if (mVideoWidth * height < width * mVideoHeight) {
                    //Log.i("@@@", "image too wide, correcting");
                    width = height * mVideoWidth / mVideoHeight;
                } else if (mVideoWidth * height > width * mVideoHeight) {
                    //Log.i("@@@", "image too tall, correcting");
                    height = width * mVideoHeight / mVideoWidth;
                }

                videoView.setVideoViewSize(width, height);
                //Log.e("MyTAG","视频videoWidth="+videoWidth+"   视频videoHeight="+videoHeight);
                //Log.e("MyTAG","新width="+width+"   新height="+height);


                llLoading.setVisibility(View.GONE);
                handler.postDelayed(runnable, 0);
                //handler.sendEmptyMessageDelayed(Constants.HANDLER_VIDEO_RESUME, 500);
                handler.sendEmptyMessage(Constants.HANDLER_VIDEO_RESUME);

            }
        });


        //播放出错
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                if(!hasError){
                    hasError=true;
                    return false;
                }else{
                    return true;
                }

            }
        });

        //播放完成的监听
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                llLoading.setVisibility(View.VISIBLE);
                //置空
                mp.setDisplay(null);
                mp.reset();
                //播放下一个视频
                playNext();
            }
        });


        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {

                newsList.clear();
                setNews();
            }
        });

        lvListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //http://oa.ybqtw.org.cn/api/Html/news_show.html?method=newshtml&userid=1&Key=21218CCA77804D2BA1922C33E0151105&typeVer=&id=1419
                String url = Constants.BASE_NEWS_URL + "method=newshtml" + "&userid=" + SharedPreUtil.getValue(getApplicationContext(), "userinfo", "UserId", "")
                        + "&Key=" + SharedPreUtil.getValue(getApplicationContext(), "userinfo", "Key", "") + "&id=" + newsList.get(position).getNewsId();
                Log.e("MyTAG", "url=" + url);
                Intent intent = new Intent(MainActivity.this, ZcxxNewsDetailActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("from", "mainNews");
                startActivity(intent);
            }
        });
    }

    private List<MainNew.DataBean> newsList;

    /**
     * 设置新闻数据
     */
    private void setNews() {

        if (newsList == null) {
            newsList = new ArrayList<>();
        }
        Map map = new HashMap();//method=IndexNews&Page=1&PageSize=6&TVInfoId=1&Key=21218CCA77804D2BA1922C33E0151105
        map.put("method", "IndexNews");
        map.put("Page", "1");
        map.put("PageSize", "6");
        map.put("TVInfoId", SharedPreUtil.getValue(this, "userinfo", "TVInfoId", ""));
        map.put("Key", SharedPreUtil.getValue(this, "userinfo", "Key", ""));
        //获取数据
        MyApplication.okHttpUtil.post(Constants.BASE_URL, map, new HttpCallBack() {
            @Override
            public void OnSuccess(String jsonStr) {
                //Log.e("MyTAG", "主页新闻:" + jsonStr);
                MainNew mainNew = JSON.parseObject(jsonStr, MainNew.class);
                // newsList = mainNew.getData();
                newsList.clear();
                newsList.addAll(mainNew.getData());
                handler.sendEmptyMessage(Constants.HANDLER_MAINNEWS_RECEIVED);

            }

            @Override
            public void OnError(String jsonStr) {
                handler.sendEmptyMessage(Constants.HANDLER_NET_ERROR);//没有网络
                materialRefreshLayout.finishRefresh();//用于关闭刷新
            }
        });
//        for (int i = 0; i < 5; i++) {
//            MainNew mainNew = new MainNew();
//            mainNew.setTitle("区工商联专题学习区纪委二十四届二次全会精神");
//            mainNew.setEditDate("");
//            mainNew.setDeptName("公告");
//            mainNewList.add(mainNew);
//        }
    }

    /**
     * 设置新闻适配器
     */
    private void setAdapter() {

        if (adapter == null) {//第一次加载
            adapter = new MainNewsAdapter(getApplicationContext(), newsList);
            lvListview.setAdapter(adapter);
        } else {//刷新
            adapter.notifyDataSetChanged();

            if (newsList.size() > 0) {
                lvListview.setSelection(0);
            }

        }
        materialRefreshLayout.finishRefresh();
    }

    /**
     * 轮播数据
     */
    private void setPmdData() {
        pmdList = new ArrayList<>();
        pmdList.add("党政主要负责同志要亲力亲为抓改革扑下身子抓落实,坚持不懈求生存,加大力度发展科技事业,为共产主义事业奋斗");


    }


    /**
     * 获取网络视频
     */
    private void getVideos() {
        MyApplication.okHttpUtil.get(Constants.NET_VIDEO_URL, new HttpCallBack() {
            @Override
            public void OnSuccess(String jsonStr) {
                //Log.e("MyTAG","获取到的json:"+jsonStr);
                video = JSON.parseObject(jsonStr, Video.class);
                videosList = video.getTrailers();
                handler.sendEmptyMessage(Constants.HANDLER_VIDEO_RECEIVEED);

            }

            @Override
            public void OnError(String jsonStr) {

            }
        });
    }


    /**
     * 播放视频
     */
    private void playVideo() {
        //设置视频播放地址
        videoUrl = videosList.get(0).getHightUrl();
        videoView.setVideoPath(videoUrl);
        videoView.requestFocus();
        videoView.start();//开始播放
    }


    private static long old_duration;
    Runnable runnable = new Runnable() {
        public void run() {
            long duration = videoView.getCurrentPosition();

            if (videoView.isPlaying()) {
                long buffer = duration - old_duration;
                if (buffer < 500) {//卡了
                    llLoading.setVisibility(View.INVISIBLE);
                } else {
                    //不卡了
                    llLoading.setVisibility(View.GONE);
                }
            } else {
                llLoading.setVisibility(View.GONE);
            }
            old_duration = duration;
            handler.postDelayed(runnable, 500);

        }
    };


    /**
     * 播放下一个视频
     */
    private void playNext() {
        videoPosition++;

        if (videoPosition == videosList.size()) {
            videoPosition = 0;
        }
        videoView.setVideoPath(videosList.get(videoPosition).getHightUrl());
        videoView.start();
        llLoading.setVisibility(View.VISIBLE);
    }


    /**
     * 页面点击事件
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.rl_videoView://视频被点击

                break;

            case R.id.ll_sqtj://诉求提交
                // MyToast.showToast(getApplicationContext(), "诉求提交被点击");
                intent.setClass(MainActivity.this, SqtjActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_zcxx://政策信息被点击
                // MyToast.showToast(getApplicationContext(), "政策信息被点击");
                intent.setClass(MainActivity.this, ZcxxActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_jgcx://结果查询被点击
                intent.setClass(MainActivity.this, JgcxActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_dqfc://党群风采被点击

                intent.setClass(MainActivity.this, DqfcActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_wyfw://物业服务被点击

                break;
            case R.id.ll_bszn://办事指南被点击

                intent.setClass(MainActivity.this, BsznActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_zwfu://政务服务被点击

                intent.setClass(MainActivity.this, ZwfwActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_shfw://社会服务被点击

                intent.setClass(MainActivity.this, ShfwActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_pmd://跑马灯文字被点击
                intent.setClass(MainActivity.this, WgglActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onPause() {

        MyApplication.lastPosition = videoPosition;
        MyApplication.lastPlayPosition = old_duration;
        MyApplication.isRestart = true;
        //videoView.pause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoView.resume();
        if (videosList != null && videosList.size() > 0) {
            videoView.setVideoPath(videosList.get(MyApplication.lastPosition).getHightUrl());
            //videoView.start();
        }
       videoView.seekTo(MyApplication.lastPlayPosition);

    }

    @Override
    protected void onStop() {
        videoView.pause();
        super.onStop();
    }

    @Override
    protected void onRestart() {
        if (videosList != null && videosList.size() > 0) {
            videoPosition = MyApplication.lastPosition;
            videoUrl = videosList.get(MyApplication.lastPosition).getHightUrl();
            old_duration = MyApplication.lastPlayPosition;
        }

        super.onRestart();

    }

    @Override
    protected void onDestroy() {
        videoView.stopPlayback();
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
