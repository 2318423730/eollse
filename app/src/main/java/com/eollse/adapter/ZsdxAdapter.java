package com.eollse.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.eollse.R;

import java.util.List;

/**
 * Created by miliang on 2017/3/28/0028.
 */

public class ZsdxAdapter extends MyBaseAdapter {
    private List<Integer> list;
    public ZsdxAdapter(Context context, List list) {
        super(context, list);
        this.list=list;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view==null){
            view=getLayoutInflater().inflate(R.layout.item_zsdx,null);
            viewHolder=new ViewHolder();
            viewHolder.iv_icon= (ImageView) view.findViewById(R.id.iv_icon);
            view.setTag(viewHolder);
        }
        viewHolder= (ViewHolder) view.getTag();
        viewHolder.iv_icon.setImageResource(list.get(i));
        return view;
    }
    class ViewHolder{
        ImageView iv_icon;
    }
}
