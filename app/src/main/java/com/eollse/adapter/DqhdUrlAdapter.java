package com.eollse.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.entity.DqhdHref;

import java.util.List;

/**
 * Created by miliang on 2017/3/24/0024.
 */

public class DqhdUrlAdapter extends MyBaseAdapter {
    private List<DqhdHref> list;

    public DqhdUrlAdapter(Context context, List list) {
        super(context, list);
        this.list = list;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = getLayoutInflater().inflate(R.layout.item_dqhd_url, null);
            viewHolder = new ViewHolder();
            viewHolder.tv_title = (TextView) view.findViewById(R.id.tv_title);
            view.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) view.getTag();
//        viewHolder.tv_title.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG ); //下划线
//        viewHolder.tv_title.getPaint().setAntiAlias(true);//抗锯齿
//        viewHolder.tv_title.setText(Html.fromHtml("*\t"+list.get(i).getUrl()));
        viewHolder.tv_title.setText(Html.fromHtml("<u>*" + list.get(i).getUrl() + "</u>"));
        return view;
    }

    class ViewHolder {
        TextView tv_title;
    }
}
