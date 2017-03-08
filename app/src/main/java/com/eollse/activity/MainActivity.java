package com.eollse.activity;



import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
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
import com.eollse.ui.MySystemVideoView;
import com.eollse.utils.Constants;
import com.eollse.utils.HttpCallBack;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;


public class MainActivity extends BaseActivity {


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
    MySystemVideoView videoView;
    @BindView(R.id.lv_listview)
    ListView lvListview;
    @BindView(R.id.ll_middle_left)
    LinearLayout llMiddleLeft;
    @BindView(R.id.ll_wyhs)
    LinearLayout llWyhs;
    @BindView(R.id.ll_zxcc)
    LinearLayout llZxcc;
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

    /**
     * 轮播文字
     */
    private String str = "";
    /**
     * 轮播数据集合
     */
    private List<String> pmdList;
    /**
     *新闻集合
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
        //新闻item监听
        lvListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(getApplicationContext(),"点击了第"+0+"条",Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 设置新闻数据
     */
    private void setNews() {
        if(mainNewList==null){
            mainNewList=new ArrayList<>();
        }
        for(int i=0;i<10;i++){
            MainNew mainNew=new MainNew();
            mainNew.setTitle("新闻标题aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+i);
            mainNew.setEditDate("2017-3-8");
            mainNew.setDeptName("区域");
            mainNewList.add(mainNew);
        }
    }

    /**
     * 设置新闻适配器
     */
    private void setAdapter() {
        adapter=new MainNewsAdapter(getApplicationContext(),mainNewList);
        lvListview.setAdapter(adapter);
    }

    /**
     *轮播数据
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
    private void getVideos(){
        MyApplication.okHttpUtil.get(Constants.NET_VIDEO_URL, new HttpCallBack() {
            @Override
            public void OnSuccess(String jsonStr) {
                //Log.e("MyTAG","获取到的json:"+jsonStr);
                video= JSON.parseObject(jsonStr,Video.class);
                videosList=video.getTrailers();
                handler.sendEmptyMessage(Constants.VIDEO_RECEIVE);
                Log.e("MyTAG","size="+videosList.size());
            }

            @Override
            public void OnError(String jsonStr) {

            }
        });
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case Constants.VIDEO_RECEIVE:
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
     * 播放视频
     */
    private void playVideo(){
        videoUrl=videosList.get(0).getHightUrl();

        videoView.setVideoPath(videoUrl);
        videoView.requestFocus();
        //准备好的监听
        videoView.setOnPreparedListener(new io.vov.vitamio.MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(io.vov.vitamio.MediaPlayer mp) {
                videoView.start();//开始播放
            }
        });


        //播放出错
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                return false;
            }
        });

        //播放完成的监听
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //置空
                mp.setDisplay(null);
                mp.reset();
                //播放下一个视频(暂时重复播放第一个)
                playNext();
            }
        });


    }

    /**
     * 播放的视频索引
     */
    private int videoPosition;
    /**
     * 播放下一个视频
     */
    private void playNext() {
        videoPosition++;
        if(videoPosition==videosList.size()){
            videoPosition=0;
        }
        videoView.setVideoPath(videosList.get(videoPosition).getHightUrl());
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
