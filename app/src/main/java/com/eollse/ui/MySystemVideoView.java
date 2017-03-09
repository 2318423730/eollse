package com.eollse.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * Created by miliang on 2017/3/8/0008.
 */

public class MySystemVideoView extends VideoView{
    public MySystemVideoView(Context context) {
        super(context,null);
    }

    public MySystemVideoView(Context context, AttributeSet attrs) {
        super(context, attrs,0);
    }

    public MySystemVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec,heightMeasureSpec);
    }
}