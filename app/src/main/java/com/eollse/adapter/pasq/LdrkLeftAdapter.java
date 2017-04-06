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

public class LdrkLeftAdapter extends MyBaseAdapter {
    private List<String> list;
    public LdrkLeftAdapter(Context context, List list) {
        super(context, list);
        this.list=list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView=getLayoutInflater().inflate(R.layout.item_ldrk_month,null);
            viewHolder=new ViewHolder();
            viewHolder.tv_month= (TextView) convertView.findViewById(R.id.tv_month);
            convertView.setTag(viewHolder);
        }
        viewHolder= (ViewHolder) convertView.getTag();
        viewHolder.tv_month.setText(list.get(position));
        if(selectIndex==position){
            viewHolder.tv_month.setSelected(true);
        }else{
            viewHolder.tv_month.setSelected(false);
        }
        return convertView;
    }

    private static class ViewHolder {
        private TextView tv_month ;

    }
    private int selectIndex;
    public void setSelectIndex(int i){
        selectIndex = i;
    }
}
