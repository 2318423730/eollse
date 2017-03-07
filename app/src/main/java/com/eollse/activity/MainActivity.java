package com.eollse.activity;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;


import com.eollse.R;
import com.eollse.ui.MyPmdTextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    public static final int HANDLER_TXT_SCROLL_OVER = 1;//一串文字滚动完成(跑马灯)
    private MyPmdTextView myPmdTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myPmdTextView= (MyPmdTextView) findViewById(R.id.tv_pmd);
        
        setPmdData();
//        Log.e("MyTAG", "获取高度:"+myPmdTextView.getMeasuredWidth());
//        if(myPmdTextView.getWidth()>0){
//            Log.e("MyTAG", "获取高度:"+myPmdTextView.getWidth());
            myPmdTextView.setHandler(mHandler);
            myPmdTextView.setStr(pmdList.get(0));
 //       }




    }
    private List<String> pmdList;
    private void setPmdData() {
        pmdList=new ArrayList<>();
        pmdList.add("第一组");
        pmdList.add("第二组");
        pmdList.add("第三组");
        pmdList.add("第四组");
        pmdList.add("第五组");

    }

    private int i=1;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
           switch (msg.what){
               case HANDLER_TXT_SCROLL_OVER:
                   myPmdTextView.setStr(pmdList.get(i));
                   Log.e("MyTAG","换另一组文字滚动");
                   i++;
                   if(i==pmdList.size()){
                       i=0;
                   }
                   break;
           }
        }
    };

    
}
