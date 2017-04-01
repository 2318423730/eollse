package com.eollse.activity.zwfw.jzbf;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.eollse.R;
import com.eollse.adapter.jzbf.BfzcAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/3/31.
 * 帮扶政策
 */

public class BfzcFragment extends Fragment {
    @BindView(R.id.lv_info)
    ListView lvInfo;
    Unbinder unbinder;

    private List<String> list = new ArrayList<>();
    private BfzcAdapter bfzcAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jzbf_bfzc, null);
        unbinder = ButterKnife.bind(this, view);

        for(int i=0;i<15;i++){
            list.add("关于印发《贫困残疾人脱贫攻坚行动计划(2016-2020年)》的通知");
        }
        bfzcAdapter=new BfzcAdapter(getActivity().getApplicationContext(),list);
        lvInfo.setAdapter(bfzcAdapter);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
