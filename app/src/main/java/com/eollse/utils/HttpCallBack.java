package com.eollse.utils;

import okhttp3.Response;

/**
 * Created by miliang on 2016/11/2/0002.
 */

public interface HttpCallBack {

    void OnSuccess(String json);

    void OnError(String json);
}
