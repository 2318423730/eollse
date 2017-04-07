package com.eollse.adapter.pasq;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.adapter.MyBaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/4/6/0006.
 */

public class PasqLeftAdapter extends MyBaseAdapter {
    private List<String> list;
    public PasqLeftAdapter(Context context, List list) {
        super(context, list);
        this.list=list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView=getLayoutInflater().inflate(R.layout.item_pasq_left,null);
            viewHolder=new ViewHolder();
            viewHolder.tv_info= (TextView) convertView.findViewById(R.id.tv_info);
            convertView.setTag(viewHolder);
        }
        viewHolder= (ViewHolder) convertView.getTag();
        viewHolder.tv_info.setText(list.get(position));
        return convertView;
    }
    class ViewHolder{
        TextView tv_info;
    }
}
