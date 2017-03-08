package com.eollse.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;

import io.vov.vitamio.widget.VideoView;


/**
 * Created by miliang on 2017/3/8/0008.
 */

public class MyVitamioVideoView extends VideoView {
    private int videoWidth;
    private int videoHeight;
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
        Log.e("MyTAG","onMeasure");
        //if(videoWidth==0||videoHeight==0){
            setMeasuredDimension(widthMeasureSpec,heightMeasureSpec);
       // }else
       // setMeasuredDimension(videoWidth,videoWidth);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.e("MyTAG","w="+w+"   h="+h+"   oldw="+oldh+"   oldh="+oldh);

        videoWidth=w;
        videoHeight=h;
    }

    public void setNewSize(){
        ViewGroup.LayoutParams params=getLayoutParams();
        params.width=videoWidth;
        params.height=videoHeight;
        setLayoutParams(params);
    }
}
