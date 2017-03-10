package com.eollse.utils;

/**
 * Created by miliang on 2016/12/16/0016.
 */

public interface Constants {

     int HANDLER_VIDEO_RECEIVE=1;
    /**
     * 视频继续上次播放
     */
     int HANDLER_VIDEO_RESUME=2;
    /**
     * 视频是否卡顿
     */
     int HANDLER_VIDEO_BUFFERING=3;

    /**
     * 网络视频的联网地址
     */
    public static  final  String NET_VIDEO_URL = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
    /**
     * 网络地址
     */
    String BASE_URL="http://oa.ybqtw.org.cn/api/APP1.0.aspx?";

}
