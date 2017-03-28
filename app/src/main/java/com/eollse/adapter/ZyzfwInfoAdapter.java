package com.eollse.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.entity.Zyzfw;

import java.util.List;

/**
 * Created by miliang on 2017/3/28/0028.
 */

public class ZyzfwInfoAdapter extends MyBaseAdapter {
    private List<Zyzfw> list;
    public ZyzfwInfoAdapter(Context context, List list) {
        super(context, list);
        this.list=list;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view==null){
            view=getLayoutInflater().inflate(R.layout.item_zyzfw,null);
            viewHolder=new ViewHolder();
            viewHolder.iv_icon= (ImageView) view.findViewById(R.id.iv_icon);
            viewHolder.tv_title= (TextView) view.findViewById(R.id.tv_title);
            viewHolder.tv_person= (TextView) view.findViewById(R.id.tv_person);
            viewHolder.tv_time= (TextView) view.findViewById(R.id.tv_time);
            view.setTag(viewHolder);
        }
        viewHolder= (ViewHolder) view.getTag();
        viewHolder.iv_icon.setImageResource(list.get(i).getIconId());
        viewHolder.tv_title.setText(list.get(i).getTitle());
        viewHolder.tv_person.setText(list.get(i).getPerson());
        viewHolder.tv_time.setText(list.get(i).getTime());
        return view;
    }
    class ViewHolder{
        ImageView iv_icon;
        TextView tv_title;
        TextView tv_person;
        TextView tv_time;
    }
}
