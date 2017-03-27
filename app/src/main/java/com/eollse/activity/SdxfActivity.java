package com.eollse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.adapter.SdxfAdapter;
import com.eollse.app.MyApplication;
import com.eollse.entity.Sdxf;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 时代先锋
 */
public class SdxfActivity extends BaseActivity {

    @BindView(R.id.tv_backHome)
    TextView tvBackHome;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.lv_left)
    ListView lvLeft;

    private List<Sdxf> list=new ArrayList<>();
    private SdxfAdapter sdxfAdapter;
    private String[] title;
    private String[] des;
    private int[] img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdxf);
        ButterKnife.bind(this);
        tvTitle.setText("时代先锋");
        title=new String[]{"渝北先进董事长","渝北最优总经理","全能型副总经理","1","2","3","4","5"};
        des=new String[]{"1月3日下午,重庆指讯科技有限公司李毅董事长一行来公司考察","1月3日下午,重庆指讯科技有限公司李毅董事长一行来公司考察","1月3日下午,重庆指讯科技有限公司李毅董事长一行来公司考察",
        "1","2","3","4","5",};
        img=new int[]{R.drawable.sdxf_p1,R.drawable.sdxf_p2,R.drawable.sdxf_p3,R.drawable.sdxf_p3,R.drawable.sdxf_p3,R.drawable.sdxf_p3,R.drawable.sdxf_p3,R.drawable.sdxf_p3};

        for(int i=0;i<img.length;i++){
            Sdxf sdxf=new Sdxf();
            sdxf.setIconId(img[i]);
            sdxf.setTitle(title[i]);
            sdxf.setDes(des[i]);
            list.add(sdxf);
        }
        
        setListeners();
        
        sdxfAdapter=new SdxfAdapter(getApplicationContext(),list);
        lvLeft.setAdapter(sdxfAdapter);
        
    }

    private void setListeners() {
        lvLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                sdxfAdapter.setSelectIndex(position);
                sdxfAdapter.notifyDataSetChanged();

            }
        });
    }

    @OnClick({R.id.tv_backHome, R.id.tv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_backHome:
                Intent intent = new Intent(SdxfActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                MyApplication.clearAllActivitiesWithOutMainActivity();
                break;
            case R.id.tv_back:
                finish();
                break;
        }
    }
}
