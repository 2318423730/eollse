package com.eollse.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eollse.R;

/**
 * Created by miliang on 2017/3/13/0013.
 */

public class TabFragnemt extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tab, container, false);
        TextView mTextView = (TextView) view.findViewById(R.id.tv_tabName);

        //获取Activity传递过来的参数
        Bundle mBundle = getArguments();
        String tabName = mBundle.getString("tabName");
        mTextView.setText(tabName);
        return view;
    }
}
