package com.eollse.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.entity.BsznTitle1;

import java.util.List;

/**
 * Created by Administrator on 2017/3/30.
 */

public class BsznTitle1Adapter extends MyBaseAdapter {
    private List<BsznTitle1> list;
    public BsznTitle1Adapter(Context context, List list) {
        super(context, list);
        this.list=list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView=getLayoutInflater().inflate(R.layout.item_bsznitem_title1,null);
            viewHolder=new ViewHolder();
            viewHolder.ll_bg= (LinearLayout) convertView.findViewById(R.id.ll_bg);
            viewHolder.iv_icon= (ImageView) convertView.findViewById(R.id.iv_icon);
            viewHolder.tv_title= (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.tv_des= (TextView) convertView.findViewById(R.id.tv_des);
            convertView.setTag(viewHolder);
        }
        viewHolder= (ViewHolder) convertView.getTag();
        viewHolder.tv_title.setText(list.get(position).getTitle());
        viewHolder.tv_des.setText(list.get(position).getDes());
        viewHolder.iv_icon.setImageResource(list.get(position).getIconId());
        if(selectIndex==position){
            viewHolder.ll_bg.setBackgroundResource(R.color.activity_bszn_item_selected);
        }else{
            viewHolder.ll_bg.setBackgroundResource(R.color.white);
        }
        return convertView;
    }
    class ViewHolder{
        LinearLayout ll_bg;
        ImageView iv_icon;
        TextView tv_title;
        TextView tv_des;
    }

    private int selectIndex=0;
    public void setSelectIndex(int i){
        selectIndex = i;
    }

}
