package com.eollse.activity.zwfw.dqfc;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.activity.BaseActivity;
import com.eollse.adapter.FfclAdapter;
import com.eollse.entity.Ffcl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 反腐倡廉
 */
public class FfclActivity extends BaseActivity {

    @BindView(R.id.tv_backHome)
    TextView tvBackHome;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.lv_left)
    ListView lvLeft;
    @BindView(R.id.iv_indictor1)
    ImageView ivIndictor1;
    @BindView(R.id.iv_indictor2)
    ImageView ivIndictor2;
    @BindView(R.id.iv_indictor3)
    ImageView ivIndictor3;
    @BindView(R.id.rb_new)
    RadioButton rbNew;
    @BindView(R.id.rb_lvfa)
    RadioButton rbLvfa;
    @BindView(R.id.rb_anli)
    RadioButton rbAnli;
    @BindView(R.id.rg_top)
    RadioGroup rgTop;

    private List<Ffcl> list=new ArrayList<>();
    private FfclAdapter ffclAdapter;
    private int[] img;
    private String[] title;
    private String[] time;
    private int[] zan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ffcl);
        ButterKnife.bind(this);
        tvTitle.setText("反腐倡廉");

        img=new int[]{R.drawable.ffcl_1,R.drawable.ffcl_1,R.drawable.ffcl_1};
        title=new String[]{"中纪委:深挖隐秘聚会公款吃喝严重的坚决处理","中纪委:深挖隐秘聚会公款吃喝严重的坚决处理","中纪委:深挖隐秘聚会公款吃喝严重的坚决处理"};
        time=new String[]{"2017-3-29 11:02","2017-3-29 11:02","2017-3-29 11:02"};
        zan=new int[]{100,101,102};
        for(int i=0;i<img.length;i++){
            Ffcl ffcl=new Ffcl();
            ffcl.setIconId(img[i]);
            ffcl.setTitle(title[i]);
            ffcl.setTime(time[i]);
            ffcl.setZan(zan[i]);
            list.add(ffcl);
        }

        ffclAdapter=new FfclAdapter(getApplicationContext(),list);
        lvLeft.setAdapter(ffclAdapter);

        setListeners();
    }

    private void setListeners() {
        lvLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
}
