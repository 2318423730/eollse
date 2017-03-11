package com.eollse.activity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.adapter.BsznAdapter;
import com.eollse.entity.Bszn;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BsznActivity extends BaseActivity {
    @BindView(R.id.tv_backHome)
    TextView tvBackHome;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.gv_bszn)
    GridView gvBszn;
    @BindView(R.id.rg_top)
    RadioGroup rgTop;
    @BindView(R.id.rb_geren)
    RadioButton rbGeren;
    @BindView(R.id.rb_qiye)
    RadioButton rbQiye;
    private List<Bszn> bsznGerenList;
    private List<Bszn> bsznQiyeList;
    private BsznAdapter bsznAdapter;
    private List<Bszn> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bszn);
        ButterKnife.bind(this);

        //组装数据
        getData();
        //设置Adapter
        bsznAdapter = new BsznAdapter(getApplicationContext(), list);
        gvBszn.setAdapter(bsznAdapter);

        //设置监听
        setListeners();
    }

    private void setListeners() {
        rgTop.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
                switch (checkId) {
                    case R.id.rb_geren:
                        rgTop.check(R.id.rb_geren);

                        list.clear();
                        list.addAll(bsznGerenList);
                        bsznAdapter.notifyDataSetChanged();
                        break;
                    case R.id.rb_qiye:
                        rgTop.check(R.id.rb_qiye);
                        list.clear();
                        list.addAll(bsznQiyeList);
                        bsznAdapter.notifyDataSetChanged();
                        break;
                }
            }
        });
    }

    private void getData() {
        bsznGerenList = new ArrayList<>();
        bsznQiyeList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Bszn bszn = new Bszn();
            bszn.setIconId(R.drawable.bszn_aqsc);
            bszn.setTitle("安全生产");
            bsznGerenList.add(bszn);

        }
        for (int i = 0; i < 30; i++) {
            Bszn bszn = new Bszn();
            bszn.setIconId(R.drawable.bszn_aqsc);
            bszn.setTitle("安全生产1");
            bsznQiyeList.add(bszn);
        }
        list.addAll(bsznGerenList);
    }
}
