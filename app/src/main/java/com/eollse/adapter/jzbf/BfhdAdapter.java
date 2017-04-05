package com.eollse.adapter.jzbf;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eollse.R;
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
        ViewHolder holder;
//        if(viewType==TYPE1){
//            holder= new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_bfhd1, parent, false));
//        }else{
            holder= new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_bfhd, parent, false));
     //   }

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Bfhd bfhd = list.get(position);
        holder.iv_icon.setImageResource(list.get(position).getIconId());
        holder.tv_title.setText(list.get(position).getTitle());
        holder.tv_time.setText(list.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_icon;
        TextView tv_title;
        TextView tv_time;


        public ViewHolder(View itemView) {
            super(itemView);
            iv_icon = (ImageView) itemView.findViewById(R.id.iv_icon);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_time= (TextView) itemView.findViewById(R.id.tv_time);
        }
    }

//    @Override
//    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
//        super.onAttachedToRecyclerView(recyclerView); RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
//        if(manager instanceof GridLayoutManager) {
//            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
//            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//                @Override
//                public int getSpanSize(int position) {
//                    int spanSize;
//                    if (getItemViewType(position) == TYPE1) {
//                        spanSize = 2;//跨2列
//                    }else {
//                        spanSize = 1;
//                    }
//                    return spanSize;
//                }
//            });
//        }
//
//    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return TYPE1;
        }else{
            return TYPE2;
        }
    }

    private static final int TYPE1=1;
    private static final int TYPE2=2;
}
