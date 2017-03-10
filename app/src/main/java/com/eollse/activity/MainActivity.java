package com.eollse.activity;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.eollse.R;
import com.eollse.adapter.MainNewsAdapter;
import com.eollse.app.MyApplication;
import com.eollse.entity.MainNew;
import com.eollse.entity.Video;
import com.eollse.ui.MyPmdTextView;
import com.eollse.ui.MyVitamioVideoView;
import com.eollse.utils.Constants;
import com.eollse.utils.HttpCallBack;
import com.eollse.utils.MyToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;


public class MainActivity extends BaseActivity implements OnClickListener {


    @BindView(R.id.ll_top1)
    LinearLayout llTop1;
    @BindView(R.id.iv_setting)
    ImageView ivSetting;
    @BindView(R.id.Address)
    TextView Address;
    @BindView(R.id.RealName)
    TextView RealName;
    @BindView(R.id.Mobile)
    TextView Mobile;
    @BindView(R.id.ll_top2)
    LinearLayout llTop2;
    @BindView(R.id.rl_top2)
    RelativeLayout rlTop2;
    @BindView(R.id.videoView)
    MyVitamioVideoView videoView;
    @BindView(R.id.lv_listview)
    ListView lvListview;
    @BindView(R.id.ll_middle_left)
    LinearLayout llMiddleLeft;
    @BindView(R.id.ll_wyhs)
    LinearLayout llWyhs;
    @BindView(R.id.ll_zccx)
    LinearLayout llzccx;
    @BindView(R.id.ll_cxgs)
    LinearLayout llCxgs;
    @BindView(R.id.ll_pasq)
    LinearLayout llPasq;
    @BindView(R.id.ll_wyfw)
    LinearLayout llWyfw;
    @BindView(R.id.ll_hrhs)
    LinearLayout llHrhs;
    @BindView(R.id.ll_wybs)
    LinearLayout llWybs;
    @BindView(R.id.iv_zwfu)
    ImageView ivZwfu;
    @BindView(R.id.iv_shfw)
    ImageView ivShfw;
    @BindView(R.id.iv_add)
    ImageView ivAdd;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.tv_pmd)
    MyPmdTextView tvPmd;
    @BindView(R.id.rl_videoView)
    RelativeLayout rlVideoView;
    @BindView(R.id.tv_loadingText)
    TextView tvLoadingText;
    @BindView(R.id.ll_loading)
    LinearLayout llLoading;

    /**
     * 轮播文字
     */
    private String str = "";
    /**
     * 轮播数据集合
     */
    private List<String> pmdList;
    /**
     * 新闻集合
     */
    private List<MainNew> mainNewList;
    /**
     * 主页新闻的适配器
     */
    private MainNewsAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Vitamio.isInitialized(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        MyApplication.isRestart = false;
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
        //设置新闻适配器
        setAdapter();


        getVideos();
    }

    /**
     * 设置控件监听
     */
    private void setListeners() {
        ivSetting.setOnClickListener(this);
        rlVideoView.setOnClickListener(this);
        llWyhs.setOnClickListener(this);
        llzccx.setOnClickListener(this);
        llCxgs.setOnClickListener(this);
        llPasq.setOnClickListener(this);
        llWyfw.setOnClickListener(this);
        llHrhs.setOnClickListener(this);
        llWybs.setOnClickListener(this);
        ivZwfu.setOnClickListener(this);
        ivShfw.setOnClickListener(this);
        tvPmd.setOnClickListener(this);
        ivSearch.setOnClickListener(this);
        ivAdd.setOnClickListener(this);

        //新闻item监听
        lvListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(getApplicationContext(), "点击了第" + position + "条", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 设置新闻数据
     */
    private void setNews() {
        if (mainNewList == null) {
            mainNewList = new ArrayList<>();
        }
        for (int i = 0; i < 10; i++) {
            MainNew mainNew = new MainNew();
            mainNew.setTitle("新闻标题aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + i);
            mainNew.setEditDate("2017-3-8");
            mainNew.setDeptName("类型");
            mainNewList.add(mainNew);
        }
    }

    /**
     * 设置新闻适配器
     */
    private void setAdapter() {
        adapter = new MainNewsAdapter(getApplicationContext(), mainNewList);
        lvListview.setAdapter(adapter);
    }

    /**
     * 轮播数据
     */
    private void setPmdData() {
        pmdList = new ArrayList<>();
        pmdList.add("第一组aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        pmdList.add("第二组bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
        pmdList.add("第三组cccccccccccccccccccccccccccccccccccccccccccccccc");
        pmdList.add("第四组ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
        pmdList.add("第五组eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");

    }

    /**
     * 视频类
     */
    private Video video;
    /**
     * 视频列表集合
     */
    private List<Video.TrailersBean> videosList;

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
                handler.sendEmptyMessage(Constants.HANDLER_VIDEO_RECEIVE);

            }

            @Override
            public void OnError(String jsonStr) {

            }
        });
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constants.HANDLER_VIDEO_RECEIVE:
                    //Log.e("MyTAG","控制视频播放");
                    playVideo();
                    break;
            }
        }
    };
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
     * 播放视频
     */
    private void playVideo() {
        //设置视频播放地址
        videoUrl = videosList.get(0).getHightUrl();
        videoView.setVideoPath(videoUrl);
        videoView.requestFocus();
        //准备好的监听
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {


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
                if(MyApplication.isRestart){
                    videoView.seekTo(old_duration);
                }else{
                    videoView.start();//开始播放
                }


                llLoading.setVisibility(View.GONE);
                handler.postDelayed(runnable, 0);

            }
        });


        //播放出错
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                return false;
            }
        });
        videoView.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {

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

    }


    private static long old_duration;
    Runnable runnable = new Runnable() {
        public void run() {
            long duration = videoView.getCurrentPosition();
//            if (old_duration == duration && videoView.isPlaying()) {
//                llLoading.setVisibility(View.VISIBLE);
//            } else {
//                llLoading.setVisibility(View.GONE);
//            }
//            old_duration = duration;

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
     * 当前播放的视频索引
     */
    private int videoPosition;

    /**
     * 播放下一个视频
     */
    private void playNext() {
        videoPosition++;
        if (videoPosition == videosList.size()) {
            videoPosition = 0;
        }
        videoView.setVideoPath(videosList.get(videoPosition).getHightUrl());
    }


    /**
     * 页面点击事件
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_videoView://视频被点击
                MyToast.showToast(getApplicationContext(), "视频被点击");
                break;
            case R.id.iv_setting://设置被点击
                MyToast.showToast(getApplicationContext(), "设置被点击");
                break;
            case R.id.ll_wyhs://我有话说被点击
                MyToast.showToast(getApplicationContext(), "我有话说被点击");
                break;
            case R.id.ll_zccx://政策查询被点击
                MyToast.showToast(getApplicationContext(), "政策查询被点击");
                break;
            case R.id.ll_cxgs://查询公示被点击
                MyToast.showToast(getApplicationContext(), "查询公示被点击");
                break;
            case R.id.ll_pasq://平安社区被点击
                MyToast.showToast(getApplicationContext(), "平安社区被点击");
                break;
            case R.id.ll_wyfw://物业服务被点击
                MyToast.showToast(getApplicationContext(), "物业服务被点击");
                break;
            case R.id.ll_hrhs://好人好事被点击
                MyToast.showToast(getApplicationContext(), "好人好事被点击");
                break;
            case R.id.ll_wybs://我要办事被点击
                MyToast.showToast(getApplicationContext(), "我要办事被点击");
                break;
            case R.id.iv_zwfu://政务服务被点击
                MyToast.showToast(getApplicationContext(), "政务服务被点击");
                break;
            case R.id.iv_shfw://社会服务被点击
                MyToast.showToast(getApplicationContext(), "社会服务被点击");
                break;
            case R.id.tv_pmd://跑马灯文字被点击
                MyToast.showToast(getApplicationContext(), "跑马灯文字被点击");
                break;
            case R.id.iv_search://搜索被点击
                MyToast.showToast(getApplicationContext(), "搜索被点击");
                break;
            case R.id.iv_add://增加被点击
                MyToast.showToast(getApplicationContext(), "增加被点击");
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        old_duration=MyApplication.lastPosition;
        videoView.setVideoPath(MyApplication.lastUrl);
        videoView.seekTo(old_duration);
    }

    @Override
    protected void onPause() {
        videoView.pause();
        super.onPause();
    }

    @Override
    protected void onStop() {

        MyApplication.lastPosition=old_duration;
        MyApplication.lastUrl=videosList.get(videoPosition).getHightUrl();
        MyApplication.isRestart=true;
        super.onStop();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {

        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
