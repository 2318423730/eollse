package com.eollse.activity.zwfw.jzbf;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eollse.R;
import com.eollse.adapter.jzbf.BfhdAdapter;
import com.eollse.entity.Bfhd;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/3/31.
 * 帮扶力量
 */

public class BfhdFragment extends Fragment {


    @BindView(R.id.gv_info)
    RecyclerView gvInfo;
    Unbinder unbinder;
    private List<Bfhd> list = new ArrayList<>();
    private BfhdAdapter bfhdAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jzbf_bfhd, null);

        unbinder = ButterKnife.bind(this, view);

        for (int i = 0; i < 15; i++) {
            Bfhd bfhd = new Bfhd();
            bfhd.setIconId(R.mipmap.ic_launcher);
            bfhd.setTitle("活动名称" + i);
            bfhd.setTime("2017-04-01");
            list.add(bfhd);
        }



        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();


    }
}
