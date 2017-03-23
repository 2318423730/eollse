package com.eollse.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.eollse.R;

import java.util.List;

/**
 * Created by miliang on 2017/3/23/0023.
 */

public class YybsTimeAdapter extends RecyclerView.Adapter<YybsTimeAdapter.ViewHolder> {
    private Context context;
    private List<String> list;
    private OnItemClickListener onItemClickListener;

    public YybsTimeAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_time, parent, false));


        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.btn_time.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        Button btn_time;
        public ViewHolder(View itemView) {
            super(itemView);
            btn_time = (Button) itemView.findViewById(R.id.btn_time);
            if (onItemClickListener != null) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onItemClickListener.onItemClick(view, getLayoutPosition());
                    }
                });
            }
        }
    }

    /**
     * 设置某条的监听
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 设置item的点击事件
     */
    public interface OnItemClickListener {

        void onItemClick(View view, int position);
    }
}
