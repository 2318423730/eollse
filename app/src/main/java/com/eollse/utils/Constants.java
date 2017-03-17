package com.eollse.utils;

/**
 * Created by miliang on 2016/12/16/0016.
 */

public interface Constants {
    /**
     * 获取到信息
     */
    int HANDLER_INFO_RECEIVED=-1;
    /**
     * 获取到视频资源
     */
    int HANDLER_NET_ERROR=0;
    /**
     * 获取到视频资源
     */
     int HANDLER_VIDEO_RECEIVEED=1;
    /**
     * 视频继续上次播放
     */
     int HANDLER_VIDEO_RESUME=2;
    /**
     * 视频是否卡顿
     */
     int HANDLER_VIDEO_BUFFERING=3;
    /**
     * 获取到主页新闻
     */
     int HANDLER_MAINNEWS_RECEIVED=4;
    /**
     * 获取到类型
     */
    int HANDLER_TYPE_RECEIVED=5;
    /**
     * 获取到部门
     */
    int HANDLER_DEPT_RECEIVED=6;
    /**
     * 获取到政策信息
     */
    int HANDLER_ZCXX_RECEIVED=7;
    /**
     * 诉求提交失败
     */
    int HANDLER_SQTJ_FAIL=8;
    /**
     * 登录成功
     */
    int HANDLER_LOGIN_SUCCESS=9;




    /**
     * 网络视频的联网地址
     */
    public static  final  String NET_VIDEO_URL = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
    /**
     * 新闻链接
     */
    String BASE_NEWS_URL="http://oa.ybqtw.org.cn/api/Html/news_show.html?";
    /**
     * 网络地址
     */
    String BASE_URL="http://oa.ybqtw.org.cn/api/APP1.0.aspx?";



}
