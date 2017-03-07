package com.eollse.activity;


import android.os.Bundle;


import com.eollse.R;

import com.eollse.ui.MyPmdTextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private MyPmdTextView myPmdTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myPmdTextView= (MyPmdTextView) findViewById(R.id.tv_pmd);
        
        setPmdData();


        for(int i=0;i<pmdList.size();i++){
            str+=(pmdList.get(i)+"\t\t\t\t\t\t\t\t\t\t");
        }
        myPmdTextView.setText(str);

    }
    private String str="";
    private List<String> pmdList;
    private void setPmdData() {
        pmdList=new ArrayList<>();
        pmdList.add("第一组aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        pmdList.add("第二组bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
        pmdList.add("第三组cccccccccccccccccccccccccccccccccccccccccccccccc");
        pmdList.add("第四组ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
        pmdList.add("第五组eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");

    }


    
}
