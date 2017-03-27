package com.eollse.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.entity.Sdxf;

import java.util.List;

/**
 * Created by miliang on 2017/3/27/0027.
 */

public class SdxfAdapter extends MyBaseAdapter {
    private List<Sdxf> list;
    public SdxfAdapter(Context context, List list) {
        super(context, list);
        this.list=list;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view==null){
            view=getLayoutInflater().inflate(R.layout.item_sdxf_left,null);
            viewHolder=new ViewHolder();
            viewHolder.tv_title= (TextView) view.findViewById(R.id.tv_title);
            viewHolder.tv_des= (TextView) view.findViewById(R.id.tv_des);
            viewHolder.iv_icon= (ImageView) view.findViewById(R.id.iv_icon);
            viewHolder.ll_bg= (LinearLayout) view.findViewById(R.id.ll_bg);
            view.setTag(viewHolder);
        }
        viewHolder= (ViewHolder) view.getTag();
        viewHolder.iv_icon.setImageResource(list.get(i).getIconId());
        viewHolder.tv_title.setText(list.get(i).getTitle());
        viewHolder.tv_des.setText(list.get(i).getDes());
        if(i == selectIndex){
            viewHolder.ll_bg.setBackgroundResource(R.color.sdxf_selected);
        }else{
            viewHolder.ll_bg.setBackgroundResource(R.color.sdxf_normal);
        }
        return view;
    }

    class ViewHolder{
        ImageView iv_icon;
        TextView tv_title;
        TextView tv_des;
        LinearLayout ll_bg;
    }
    private int selectIndex = 0;
    public void setSelectIndex(int i){
        selectIndex = i;

    }


}
