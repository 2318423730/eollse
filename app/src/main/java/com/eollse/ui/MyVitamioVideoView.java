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
        super(context,null);
    }

    public MyVitamioVideoView(Context context, AttributeSet attrs) {
        super(context, attrs,0);
    }

    public MyVitamioVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec,heightMeasureSpec);
    }



    /**
     * 重新设定宽高
     */
    public void setVideoViewSize(int width,int height){
        ViewGroup.LayoutParams params=getLayoutParams();
        params.width=width;
        params.height=height;
        setLayoutParams(params);
    }
}
