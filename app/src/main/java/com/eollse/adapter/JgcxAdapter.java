package com.eollse.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.entity.Jgcx;

import java.util.List;

/**
 * Created by miliang on 2017/3/17/0017.
 */

public class JgcxAdapter extends MyBaseAdapter {
    private List<Jgcx.DataBean> list;

    public JgcxAdapter(Context context, List list) {
        super(context, list);
        this.list = list;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Jgcx.DataBean dataBean = list.get(i);
        ViewHolder viewHolder=null;
        if (view == null) {
            view = getLayoutInflater().inflate(R.layout.item_jgcx, null);
            viewHolder = new ViewHolder();
            viewHolder.tv_hanleId = (TextView) view.findViewById(R.id.tv_hanleId);
            viewHolder.tv_handleClassName = (TextView) view.findViewById(R.id.tv_handleClassName);
            viewHolder.tv_theme = (TextView) view.findViewById(R.id.tv_theme);
            viewHolder.tv_sqDate = (TextView) view.findViewById(R.id.tv_sqDate);
            viewHolder.tv_bjDate = (TextView) view.findViewById(R.id.tv_bjDate);
            viewHolder.tv_status = (TextView) view.findViewById(R.id.tv_status);
            view.setTag(viewHolder);

        }
        viewHolder = (ViewHolder) view.getTag();
        viewHolder.tv_hanleId.setText(""+dataBean.getOpinionId());
        viewHolder.tv_handleClassName.setText(dataBean.getOpinionClassName());
        viewHolder.tv_theme.setText(dataBean.getTitle());
        viewHolder.tv_sqDate.setText(dataBean.getEditDate());
        viewHolder.tv_bjDate.setText(dataBean.getReDate());

        viewHolder.tv_status.setText(""+dataBean.getAuditName());
        if (list.get(i).getAuditName().equals("已办结")) {
            viewHolder.tv_status.setTextColor(Color.parseColor("#33ff00"));
        } else if (list.get(i).getAuditName().equals("已受理")) {
            viewHolder.tv_status.setTextColor(Color.parseColor("#00ffaa"));
        }
        return view;
    }

    class ViewHolder {
        private TextView tv_hanleId;
        private TextView tv_handleClassName;
        private TextView tv_theme;
        private TextView tv_sqDate;
        private TextView tv_bjDate;
        private TextView tv_status;

    }
}
