package com.eollse.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.entity.DqhdHref;

import java.util.List;

/**
 * Created by miliang on 2017/3/24/0024.
 */

public class DqhdUrlAdapter extends MyBaseAdapter {
    private List<DqhdHref> list;
    public DqhdUrlAdapter(Context context, List list) {
        super(context, list);
        this.list=list;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view==null){
            view=getLayoutInflater().inflate(R.layout.item_dqhd_url,null);
            viewHolder=new ViewHolder();
            viewHolder.tv_title= (TextView) view.findViewById(R.id.tv_title);
            view.setTag(viewHolder);
        }
        viewHolder= (ViewHolder) view.getTag();
        viewHolder.tv_title.setText(list.get(i).getTitle());
        return view;
    }
    class ViewHolder{
        TextView tv_title;
    }
}
