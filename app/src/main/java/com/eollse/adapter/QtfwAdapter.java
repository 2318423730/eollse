package com.eollse.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.entity.Bszn;
import com.eollse.entity.Qtfw;

import java.util.List;

/**
 * Created by miliang on 2017/3/10/0010.
 */

public class QtfwAdapter extends MyBaseAdapter {
    private List<Qtfw> list;
    public QtfwAdapter(Context context, List list) {
        super(context, list);
        this.list=list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        Qtfw qtfw=list.get(position);
        ViewHolder viewHolder;
        if(convertView==null){
            convertView=getLayoutInflater().inflate(R.layout.item_qtfw,null);
            viewHolder=new ViewHolder();

            viewHolder.iv= (ImageView) convertView.findViewById(R.id.iv_icon);
            viewHolder.title= (TextView) convertView.findViewById(R.id.tv_title);
            convertView.setTag(viewHolder);

        }
        viewHolder= (QtfwAdapter.ViewHolder) convertView.getTag();

        String title=qtfw.getTitle();
        viewHolder.iv.setImageResource(qtfw.getIconId());
        viewHolder.title.setText(title);
        return convertView;
    }

    class ViewHolder{
        private ImageView iv;
        private TextView title;
    }
}
