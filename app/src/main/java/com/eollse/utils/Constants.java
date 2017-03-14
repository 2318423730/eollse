package com.eollse.utils;

/**
 * Created by miliang on 2016/12/16/0016.
 */

public interface Constants {

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
    int HANDLER_DEPT_RECEIVED=5;

    /**
     * 网络视频的联网地址
     */
    public static  final  String NET_VIDEO_URL = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
    /**
     * 网络地址
     */
    String BASE_URL="http://oa.ybqtw.org.cn/api/APP1.0.aspx?";



}
