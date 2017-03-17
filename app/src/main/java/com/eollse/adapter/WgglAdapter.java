package com.eollse.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.entity.Bszn;
import com.eollse.entity.Wggl;

import java.util.List;

/**
 * Created by miliang on 2017/3/17/0017.
 * 3列的gridView
 */

public class WgglAdapter extends MyBaseAdapter {
    private List<Object> list;
    public WgglAdapter(Context context, List list) {
        super(context, list);
        this.list=list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        Wggl wggl= (Wggl) list.get(position);
        ViewHolder viewHolder;
        if(convertView==null){
            convertView=getLayoutInflater().inflate(R.layout.item_wggl,null);
            viewHolder=new WgglAdapter.ViewHolder();

            viewHolder.iv= (ImageView) convertView.findViewById(R.id.iv_icon);
            viewHolder.title= (TextView) convertView.findViewById(R.id.tv_title);
            convertView.setTag(viewHolder);

        }
        viewHolder= (WgglAdapter.ViewHolder) convertView.getTag();

        String title=wggl.getTitle();
        viewHolder.iv.setImageResource(wggl.getIconId());
        viewHolder.title.setText(title);
        return convertView;
    }

    class ViewHolder{
        private ImageView iv;
        private TextView title;
    }
}
