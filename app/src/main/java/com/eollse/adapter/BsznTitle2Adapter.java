package com.eollse.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eollse.R;

import java.util.List;

/**
 * Created by Administrator on 2017/3/30.
 */

public class BsznTitle2Adapter extends MyBaseAdapter {
    private List<String> list;

    public BsznTitle2Adapter(Context context, List list) {
        super(context, list);
        this.list=list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView=getLayoutInflater().inflate(R.layout.item_bsznitem_title2,null);
            viewHolder=new ViewHolder();
            viewHolder.ll_bg= (LinearLayout) convertView.findViewById(R.id.ll_bg);
            viewHolder.tv_title= (TextView) convertView.findViewById(R.id.tv_title);
            convertView.setTag(viewHolder);
        }
        viewHolder= (ViewHolder) convertView.getTag();
        viewHolder.tv_title.setText(list.get(position));
        if(selectIndex==position){
            viewHolder.tv_title.setBackgroundResource(R.drawable.shape_bsznitem_title2_selected_bg);
        }else{
            viewHolder.tv_title.setBackgroundResource(R.drawable.shape_bsznitem_title2_unselected_bg);
        }
        return convertView;
    }
    class ViewHolder{
        LinearLayout ll_bg;
        TextView tv_title;
    }
    private int selectIndex=0;
    public void setSelectIndex(int i){
        selectIndex = i;
    }
}
