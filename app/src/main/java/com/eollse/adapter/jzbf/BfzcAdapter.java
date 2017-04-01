package com.eollse.adapter.jzbf;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.adapter.MyBaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */

public class BfzcAdapter extends MyBaseAdapter {
    private List<String> list;
    public BfzcAdapter(Context context, List list) {
        super(context, list);
        this.list=list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView=getLayoutInflater().inflate(R.layout.item_jzbf_bfzc,null);
            viewHolder=new ViewHolder();
            viewHolder.tv_info= (TextView) convertView.findViewById(R.id.tv_info);
            convertView.setTag(viewHolder);
        }
        viewHolder= (ViewHolder) convertView.getTag();
        viewHolder.tv_info.setText(".\t"+list.get(position));
        return convertView;
    }
    class ViewHolder{
        TextView tv_info;
    }
}
