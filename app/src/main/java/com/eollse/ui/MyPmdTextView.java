package com.eollse.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;

import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.eollse.activity.MainActivity;

/**
 * Created by miliang on 2017/3/7/0007.
 */

public class MyPmdTextView extends TextView {
    Paint mpaint = new Paint();
    private String str;//文字
    private int textWidth;//文字宽度
    private boolean isOver = false;//文字跑马灯完成
    private int myTextViewWidth;//控件宽度
    private int pointX, pointY = 20;

    public MyPmdTextView(Context context) {
        super(context, null);

    }

    public MyPmdTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mpaint.setColor(Color.RED);
        mpaint.setAntiAlias(true);
        mpaint.setStyle(Paint.Style.STROKE);
        mpaint.setTextSize(20);
    }




    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        myTextViewWidth = w;
        pointY = h;
        Log.e("MyTAG", "onSizeChanged控件宽度" + w);
        isOver = false;
        textWidth = (int) mpaint.measureText(str);
        Log.e("MyTAG", "文字宽度" + textWidth);

        pointX = myTextViewWidth;
        Log.e("MyTAG", "x坐标被赋值" + pointX);
        setStr(str,0);
    }

    @Override
    public void draw(Canvas canvas) {

        canvas.drawText(str, pointX, pointY / 2, mpaint);
        super.draw(canvas);
    }

    /**
     * 设置文字
     *
     * @param string
     */
    public void setStr(String string) {
        str = string;
        if(pointX>0){
            isOver=false;
            pointX = myTextViewWidth;
            pmdStart();
        }
    }
    public void setStr(String string,int i) {
        pmdStart();

    }
    private void pmdStart(){
        myThread = new MyThread();
        myThread.start();
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    private Handler handler;
    private MyThread myThread;

    private class MyThread extends Thread {
        @Override
        public void run() {
            if (!isOver) {
                pointX -= 2;
            }
            if (pointX < -textWidth) {
                isOver = true;
                Log.e("MyTAG", "完成");
                pointX = myTextViewWidth;
                handler.sendEmptyMessage(MainActivity.HANDLER_TXT_SCROLL_OVER);

            }
            postInvalidate();

            postDelayed(this, 20);
        }
    }
}
