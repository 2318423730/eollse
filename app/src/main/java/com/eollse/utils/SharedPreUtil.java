package com.eollse.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by miliang on 2017/3/16/0016.
 */

public class SharedPreUtil {

    //获取Editor实例
    public static SharedPreferences.Editor getEditor(Context context, String name) {
        return getSharedPreferences(context, name).edit();
    }

    //获取SharedPreferences实例
    public static SharedPreferences getSharedPreferences(Context context, String name) {
        return context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    /**
     * 向SharedPreferences中写入String类型的数据
     *
     * @param context 上下文环境
     * @param name 对应的xml文件名称
     * @param key 键
     * @param value 值
     */
    public static void putValue(Context context, String name, String key,
                                String value) {
        SharedPreferences.Editor sp = getEditor(context, name);
        sp.putString(key, value);
        sp.commit();
    }


    /**
     * 向SharedPreferences中写入boolean类型的数据
     *
     * @param context 上下文环境
     * @param name 对应的xml文件名称
     * @param key 键
     * @param value 值
     */
    public static void putValue(Context context, String name, String key,
                                boolean value) {
        SharedPreferences.Editor sp = getEditor(context, name);
        sp.putBoolean(key, value);
        sp.commit();
    }

    /**
     * 从SharedPreferences中读取long类型的数据
     *
     * @param context 上下文环境
     * @param name 对应的xml文件名称
     * @param key 键
     * @param defValue 如果读取不成功则使用默认值
     * @return 返回读取的值
     */
    public static long getValue(Context context, String name, String key,
                                long defValue) {
        SharedPreferences sp = getSharedPreferences(context, name);
        long value = sp.getLong(key, defValue);
        return value;
    }

    /**
     * 从SharedPreferences中读取float类型的数据
     *
     * @param context 上下文环境
     * @param name 对应的xml文件名称
     * @param key 键
     * @param defValue 如果读取不成功则使用默认值
     * @return 返回读取的值
     */
    public static float getValue(Context context, String name, String key,
                                 float defValue) {
        SharedPreferences sp = getSharedPreferences(context, name);
        float value = sp.getFloat(key, defValue);
        return value;
    }

    /**
     * 从SharedPreferences中读取String类型的数据
     *
     * @param context 上下文环境
     * @param name 对应的xml文件名称
     * @param key 键
     * @param defValue 如果读取不成功则使用默认值
     * @return 返回读取的值
     */
    public static String getValue(Context context, String name, String key,
                                  String defValue) {
        SharedPreferences sp = getSharedPreferences(context, name);
        String value = sp.getString(key, defValue);
        return value;
    }

    /**
     * 从SharedPreferences中读取boolean类型的数据
     *
     * @param context 上下文环境
     * @param name 对应的xml文件名称
     * @param key 键
     * @param defValue 如果读取不成功则使用默认值
     * @return 返回读取的值
     */
    public static boolean getValue(Context context, String name, String key,
                                  boolean defValue) {
        SharedPreferences sp = getSharedPreferences(context, name);
        boolean value = sp.getBoolean(key, false);
        return value;
    }
}
