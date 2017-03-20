package com.eollse.utils;

import android.util.Log;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by miliang on 2016/11/2/0002.
 */

public class OkHttpUtil {

    private static OkHttpClient okHttpClient;

    //设置私有的构造方法，不允许new
    private OkHttpUtil() {
        if (okHttpClient == null) {
            synchronized (OkHttpUtil.class) {
                if (okHttpClient == null) {
                    okHttpClient = new OkHttpClient();
                }
            }
        }

    }

    //创建方法生成类对象
    public static OkHttpUtil getInstance() {

        return new OkHttpUtil();
    }

    /**
     * 执行请求(异步)
     *
     * @param request
     */
    private void doRequest(Request request, final HttpCallBack httpCallBack) {
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                httpCallBack.OnError(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = "";
                if (response.isSuccessful()) {
                    if (null != response.cacheResponse()) {
                        str = response.cacheResponse().toString();
                        Log.i("MyTAG", "cache---" + str);
                    } else {
                        str = response.body().string();
                        Log.i("MyTAG", "network---" + str);
                    }
                    if (!"".equals(str) && str != null) {
                        httpCallBack.OnSuccess(str);
                    } else {
                        httpCallBack.OnError("空数据");
                    }

                } else {
                    httpCallBack.OnError("数据出错");
                }
            }
        });
    }

    /**
     * get方法
     *
     * @param url 请求路径
     */
    public void get(String url, HttpCallBack httpCallBack) {
        Request request = buildRequest(url, null, HttpType.GET);
        doRequest(request, httpCallBack);
    }

    /**
     * post方法
     *
     * @param url    请求路径
     * @param params 请求参数的集合
     */
    public void post(String url, Map<String, String> params, HttpCallBack httpCallBack) {
        Request request = buildRequest(url, params, HttpType.POST);
        doRequest(request, httpCallBack);
    }


    private Request buildRequest(String url, Map<String, String> params, HttpType type) {
        Request.Builder builder = new Request.Builder();
        builder.url(url);

        if (type == HttpType.GET) {//get请求
            builder.get();
        } else if (type == HttpType.POST) {//post请求
            RequestBody body = buildFormatData(params);


            //.build();
            builder.post(body);
        }

        return builder.build();
    }

    private RequestBody buildFormatData(Map<String, String> params) {
        FormBody.Builder builder = new FormBody.Builder();

        if (params != null) {//把参数添加到builder
            for (Map.Entry<String, String> en : params.entrySet()) {
                builder.add(en.getKey(), en.getValue());
            }
        }

        return builder.build();
    }


    enum HttpType {
        GET,
        POST
    }
}
