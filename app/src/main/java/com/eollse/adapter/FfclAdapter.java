package com.eollse.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.entity.Ffcl;

import java.util.List;

/**
 * Created by Administrator on 2017/3/29.
 */

public class FfclAdapter extends MyBaseAdapter {
    private List<Ffcl> list;
    public FfclAdapter(Context context, List list) {
        super(context, list);
        this.list=list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView=getLayoutInflater().inflate(R.layout.item_ffcl,null);
            viewHolder=new ViewHolder();
            viewHolder.iv_icon= (ImageView) convertView.findViewById(R.id.iv_icon);
            viewHolder.tv_title= (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.tv_time= (TextView) convertView.findViewById(R.id.tv_time);
            viewHolder.tv_zan= (TextView) convertView.findViewById(R.id.tv_zan);
            convertView.setTag(viewHolder);
        }
        viewHolder= (ViewHolder) convertView.getTag();
        viewHolder.tv_title.setText(list.get(position).getTitle());
        viewHolder.tv_zan.setText("赞"+list.get(position).getZan());
        viewHolder.tv_time.setText(list.get(position).getTime());
        viewHolder.iv_icon.setImageResource(list.get(position).getIconId());
        return convertView;
    }
    class ViewHolder{
        ImageView iv_icon;
        TextView tv_title;
        TextView tv_time;
        TextView tv_zan;
    }
}
