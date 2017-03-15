package com.eollse.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.entity.Bszn;
import com.eollse.entity.Shfw;

import java.util.List;

/**
 * Created by miliang on 2017/3/10/0010.
 */

public class ShfwAdapter extends MyBaseAdapter {
    private List<Shfw> list;
    public ShfwAdapter(Context context, List list) {
        super(context, list);
        this.list=list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        Shfw bszn=list.get(position);
        ViewHolder viewHolder;
        if(convertView==null){
            convertView=getLayoutInflater().inflate(R.layout.item_shfw,null);
            viewHolder=new ViewHolder();

            viewHolder.iv= (ImageView) convertView.findViewById(R.id.iv_icon);
            viewHolder.title= (TextView) convertView.findViewById(R.id.tv_title);
            convertView.setTag(viewHolder);

        }
        viewHolder= (ShfwAdapter.ViewHolder) convertView.getTag();

        String title=bszn.getTitle();
        viewHolder.iv.setImageResource(bszn.getIconId());
        viewHolder.title.setText(title);
        return convertView;
    }

    class ViewHolder{
        private ImageView iv;
        private TextView title;
    }
}
