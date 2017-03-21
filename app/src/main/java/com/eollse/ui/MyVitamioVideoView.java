package com.eollse.ui;

import android.content.Context;
import android.util.AttributeSet;

import android.view.ViewGroup;

import io.vov.vitamio.widget.VideoView;


/**
 * Created by miliang on 2017/3/8/0008.
 */

public class MyVitamioVideoView extends VideoView {

    public MyVitamioVideoView(Context context) {
        this(context, null);
    }

    public MyVitamioVideoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyVitamioVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //我们重新计算高度
        int width = getDefaultSize(0, widthMeasureSpec);
        int height = getDefaultSize(0, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    public void setVideoViewSize(int width, int height) {
        ViewGroup.LayoutParams params = getLayoutParams();
        params.width = width;
        params.height = height;
        setLayoutParams(params);
    }
}
