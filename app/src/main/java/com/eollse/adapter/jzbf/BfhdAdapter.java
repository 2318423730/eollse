package com.eollse.adapter.jzbf;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.adapter.MyBaseAdapter;
import com.eollse.entity.Bfhd;

import java.util.List;

/**
 * Created by Administrator on 2017/4/1/0001.
 */

public class BfhdAdapter extends RecyclerView.Adapter<BfhdAdapter.ViewHolder> {

    private Context context;
    private List<Bfhd> list;

    public BfhdAdapter(Context context, List<Bfhd> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_bfhd2, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Bfhd bfhd=list.get(position);
        holder.iv_icon.setImageResource(list.get(position).getIconId());
        holder.tv_title.setText(list.get(position).getTitle());
        holder.tv_time.setText(list.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_icon;
        TextView tv_title;
        TextView tv_time;


        public ViewHolder(View itemView) {
            super(itemView);
        }
    }





}
