package com.eollse.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by miliang on 2017/3/7/0007.
 */

public class MyPmdTextView extends TextView {
    public MyPmdTextView(Context context) {
        super(context,null);
    }

    public MyPmdTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public MyPmdTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    public boolean isFocused() {
        return true;
    }
}
