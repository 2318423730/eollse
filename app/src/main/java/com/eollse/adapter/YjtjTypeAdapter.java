package com.eollse.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eollse.R;


import java.util.List;

/**
 * Created by miliang on 2017/3/13/0013.
 */

public class YjtjTypeAdapter extends MyBaseAdapter {
    private List<String> list;
    public YjtjTypeAdapter(Context context, List list) {
        super(context, list);
        this.list=list;
    }

    @Override
    public View getView(int positon, View convertView, ViewGroup viewGroup) {
        String s=list.get(positon);
        ViewHolder viewHolder;
        if(convertView == null){
            convertView=getLayoutInflater().inflate(R.layout.item_popup_yjtjcontent_type,null);
            viewHolder=new ViewHolder();
            viewHolder.tv_type= (TextView) convertView.findViewById(R.id.tv_type);
            convertView.setTag(viewHolder);
        }
        viewHolder= (ViewHolder) convertView.getTag();
        viewHolder.tv_type.setText(s);
        return convertView;
    }

    class ViewHolder{
        private TextView tv_type;
    }
}