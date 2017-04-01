package com.eollse.activity.zwfw.jzbf;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eollse.R;

/**
 * Created by Administrator on 2017/3/31.
 * 帮扶力量
 */

public class BfdxFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jzbf_bfdx, null);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
