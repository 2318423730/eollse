package com.eollse.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.eollse.R;
import com.eollse.entity.MainNew;

import java.util.List;

/**
 * Created by miliang on 2017/3/8/0008.
 */

public class MainNewsAdapter extends MyBaseAdapter {
    private List<MainNew.DataBean> mainNewList;
    public MainNewsAdapter( Context context,List list) {
        super(context,list);
        mainNewList=list;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        MainNew.DataBean dataBean=mainNewList.get(position);
        ViewHolder viewHolder;
        if(view==null){
            view=getLayoutInflater().inflate(R.layout.item_main_news,null);
            viewHolder=new ViewHolder();
            viewHolder.tv_title= (TextView) view.findViewById(R.id.tv_title);
            viewHolder.tv_editDate= (TextView) view.findViewById(R.id.tv_editDate);
            view.setTag(viewHolder);
        }
        viewHolder= (ViewHolder) view.getTag();

        viewHolder.tv_title.setText("["+dataBean.getDeptName()+"]\t"+dataBean.getTitle());
        viewHolder.tv_editDate.setText(dataBean.getEditDate());
        return view;
    }

    class ViewHolder{
        private TextView tv_title;
        private TextView tv_editDate;
    }
}
