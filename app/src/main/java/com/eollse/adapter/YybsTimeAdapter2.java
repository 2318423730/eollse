package com.eollse.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.eollse.R;

import java.util.List;

/**
 * Created by miliang on 2017/3/23/0023.
 */

public class YybsTimeAdapter2 extends MyBaseAdapter{
    private List<String> list;
    private int selectIndex = -1;
    public YybsTimeAdapter2(Context context, List list) {
        super(context, list);
        this.list=list;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view==null){
            view=getLayoutInflater().inflate(R.layout.item_time,null);
            viewHolder=new ViewHolder();
            viewHolder.btn_time= (Button) view.findViewById(R.id.btn_time);
            view.setTag(viewHolder);
        }
        viewHolder= (ViewHolder) view.getTag();
        viewHolder.btn_time.setText(list.get(i));

        return view;
    }
    class ViewHolder{
        Button btn_time;
    }
    public void setSelectIndex(int i){
        selectIndex = i;

    }
}
