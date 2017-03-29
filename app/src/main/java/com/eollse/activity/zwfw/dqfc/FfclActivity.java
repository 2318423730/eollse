package com.eollse.activity.zwfw.dqfc;

import android.content.Intent;
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
import com.eollse.activity.MainActivity;
import com.eollse.adapter.FfclAdapter;
import com.eollse.app.MyApplication;
import com.eollse.entity.Ffcl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
                ffclAdapter.setSelectIndex(position);
                ffclAdapter.notifyDataSetChanged();
                if (position == list.size()-1) {//选中倒数第一条
                    ivIndictor1.setVisibility(View.GONE);
                    ivIndictor2.setVisibility(View.GONE);
                    ivIndictor3.setVisibility(View.VISIBLE);
                } else if (position==list.size()-2) {//选中倒数第二条
                    ivIndictor1.setVisibility(View.GONE);
                    ivIndictor2.setVisibility(View.VISIBLE);
                    ivIndictor3.setVisibility(View.GONE);
                } else {//否则，把选中的条目设置成可视的第一条
                    lvLeft.setSelectionFromTop(position, position - lvLeft.getFirstVisiblePosition());
                    ivIndictor1.setVisibility(View.VISIBLE);
                    ivIndictor2.setVisibility(View.GONE);
                    ivIndictor3.setVisibility(View.GONE);
                }
            }
        });
    }

    @OnClick({R.id.tv_backHome, R.id.tv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_backHome:
                Intent intent = new Intent(FfclActivity.this, MainActivity.class);
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
