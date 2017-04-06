package com.eollse.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eollse.R;

import java.util.List;

/**
 * Created by miliang on 2017/3/24/0024.
 */

public class YybsTypeAdapter extends MyBaseAdapter {
    private List<String> list;

    public YybsTypeAdapter(Context context, List list) {
        super(context, list);
        this.list = list;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = getLayoutInflater().inflate(R.layout.item_yybs_type, null);
            viewHolder = new ViewHolder();
            viewHolder.tv_type = (TextView) view.findViewById(R.id.tv_type);
            view.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) view.getTag();
        viewHolder.tv_type.setText(list.get(i));

        if (i == selectIndex) {
            viewHolder.tv_type.setSelected(true);
        } else {
            viewHolder.tv_type.setSelected(false);
        }

        return view;
    }

    class ViewHolder {
        TextView tv_type;
    }

    private int selectIndex = 0;

    public void setSelectIndex(int i){
        selectIndex = i;
    }
}
