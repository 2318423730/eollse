package com.eollse.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miliang on 2017/3/8/0008.
 */

/**
 * 万能的Adapter，
 */
public abstract class MyBaseAdapter<T> extends android.widget.BaseAdapter {
    /**
     * 数据源
     */
    private List<T> list;
    /**
     * Context
     */
    private Context context;
    /**
     * 把xml加载成view的工具
     */
    private LayoutInflater layoutInflater;

    public MyBaseAdapter( Context context,List<T> list) {
        super();
        setList(list);
        setContext(context);
        setLayoutInflater();
    }

    /**
     * 设置Context的值
     *
     * @param context
     */
    private void setContext(Context context) {
        if(context == null){
            throw new IllegalArgumentException("context不能为空");
        }
        this.context = context;
    }

    /**
     * 获取Context对象
     *
     * @return context
     */
    protected final Context getContext() {
        return context;
    }

    /**
     * 设置数据源
     */
    private void setList(List<T> list) {
        if(list==null){
            list=new ArrayList<T>();
        }
        this.list = list;
    }

    /**
     * 获取数据源
     *
     * @return List<T>
     */
    protected final List<T> getList() {
        return list;
    }

    /**
     * 获取LayoutInflater对象
     *
     * @return LayoutInflater
     */
    protected final LayoutInflater getLayoutInflater() {
        return layoutInflater;
    }

    /**
     * 设置LayoutInflater属性的值
     */
    private void setLayoutInflater() {
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

}
