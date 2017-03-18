package com.eollse.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.entity.Zcxx;

import java.util.List;

/**
 * Created by miliang on 2017/3/8/0008.
 */

public class ZcxxNewsAdapter extends MyBaseAdapter {
    private List<Zcxx.DataBean> mainNewList;

    public ZcxxNewsAdapter(Context context, List list) {
        super(context,list);
        mainNewList=list;

    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Zcxx.DataBean dataBean=mainNewList.get(position);
        ViewHolder viewHolder;
        if(view==null){
            view=getLayoutInflater().inflate(R.layout.item_news,null);
            viewHolder=new ViewHolder();
            viewHolder.tv_dept= (TextView) view.findViewById(R.id.tv_dept);
            viewHolder.tv_title= (TextView) view.findViewById(R.id.tv_title);
            viewHolder.tv_editDate= (TextView) view.findViewById(R.id.tv_editDate);
            view.setTag(viewHolder);
        }
        viewHolder= (ViewHolder) view.getTag();

        viewHolder.tv_dept.setText("["+dataBean.getDeptName()+"]");

        viewHolder.tv_dept.setTextColor(getContext().getResources().getColor(R.color.yellow));
        viewHolder.tv_title.setText("\t"+dataBean.getTitle());
        viewHolder.tv_editDate.setText("["+dataBean.getEditDate()+"]");
        return view;
    }

    class ViewHolder{
        private TextView tv_dept;
        private TextView tv_title;
        private TextView tv_editDate;
    }
}
