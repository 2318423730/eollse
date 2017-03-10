package com.eollse.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.entity.Bszn;


import java.util.List;

/**
 * Created by miliang on 2017/3/10/0010.
 */

public class BsznAdapter extends MyBaseAdapter {
    private List<Bszn> list;
    public BsznAdapter(Context context, List list) {
        super(context, list);
        this.list=list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        Bszn bszn=list.get(position);
        ViewHolder viewHolder=null;
        if(convertView==null){
            convertView=getLayoutInflater().inflate(R.layout.item_bszn,null);
            viewHolder=new ViewHolder();
            viewHolder.iv= (ImageView) convertView.findViewById(R.id.iv_icon);
            viewHolder.title= (TextView) convertView.findViewById(R.id.tv_title);
            convertView.setTag(convertView);
        }
        viewHolder= (ViewHolder) convertView.getTag();
        viewHolder.iv.setImageResource(bszn.getIconId());
        viewHolder.title.setText(bszn.getTitle());
        return convertView;
    }

    class ViewHolder{
        ImageView iv;
        TextView title;
    }
}
