package com.eollse.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.eollse.R;
import com.eollse.entity.Dept;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by miliang on 2017/3/14/0014.
 */

public class HorizontalListViewAdapter extends BaseAdapter {

    private List<Dept.DataBean> list;
    private Context mContext;
    private LayoutInflater mInflater;
    Bitmap iconBitmap;
    private int selectIndex = -1;

    public HorizontalListViewAdapter(Context context, List<Dept.DataBean> list){
        this.mContext = context;

        this.list = list;
        mInflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);//LayoutInflater.from(mContext);
    }
    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String deptName=list.get(position).getDeptName();
        ViewHolder holder;
        if(convertView==null){
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_toptitle, null);

            holder.mTitle=(TextView)convertView.findViewById(R.id.tv_topTitle);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }
        if(position == selectIndex){
            convertView.setSelected(true);
        }else{
            convertView.setSelected(false);
        }

        holder.mTitle.setText(deptName);


        return convertView;
    }

    private static class ViewHolder {
        private TextView mTitle ;

    }

    public void setSelectIndex(int i){
        selectIndex = i;

    }

}