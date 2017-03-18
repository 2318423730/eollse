package com.eollse.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.entity.JgcxContent;

import java.util.List;

/**
 * Created by miliang on 2017/3/18/0018.
 */

public class JgcxContentAdapter extends MyBaseAdapter{
    private List<JgcxContent.DataBean> list;
    public JgcxContentAdapter(Context context, List list) {
        super(context, list);
        this.list=list;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        JgcxContent.DataBean dataBean=list.get(i);
        ViewHolder viewHolder=null;
        if(view==null){
            view=getLayoutInflater().inflate(R.layout.item_jgcxcontent,null);
            viewHolder=new ViewHolder();
            viewHolder.tv_reContent= (TextView) view.findViewById(R.id.tv_reContent);
            view.setTag(viewHolder);
        }
        viewHolder= (ViewHolder) view.getTag();
        viewHolder.tv_reContent.setText(dataBean.getEditDate()+"\t"+dataBean.getReContent());
        return view;
    }
    class ViewHolder{
        private TextView tv_reContent;
    }
}
