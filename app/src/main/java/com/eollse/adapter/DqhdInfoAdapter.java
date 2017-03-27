package com.eollse.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.eollse.R;
import com.eollse.entity.Dqhd;
import com.eollse.utils.BitmapUtil;

import java.io.ByteArrayOutputStream;
import java.util.List;

import static android.R.attr.bitmap;

/**
 * Created by miliang on 2017/3/24/0024.
 */

public class DqhdInfoAdapter extends MyBaseAdapter {
    private List<Dqhd> list;
    private BitmapFactory.Options options;
    private int size = 1;
    private SimpleTarget<Bitmap> target;

    public DqhdInfoAdapter(Context context, List list) {
        super(context, list);
        this.list = list;
        options = new BitmapFactory.Options();

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if (view == null) {
            view = getLayoutInflater().inflate(R.layout.item_dqhd_info, null);
            viewHolder = new ViewHolder();
            viewHolder.iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
            viewHolder.tv_title = (TextView) view.findViewById(R.id.tv_title);
            view.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) view.getTag();
        viewHolder.tv_title.setText(list.get(i).getTitle());

//        options.inJustDecodeBounds = true;
//        BitmapFactory.decodeResource(getContext().getResources(), list.get(i).getIconId(), options);
//        size = BitmapUtil.calculateInSampleSize(options, 380, 380);
//        options.inSampleSize = size;
//        options.inJustDecodeBounds = false;
//        Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), list.get(i).getIconId(), options);
//        viewHolder.iv_icon.setImageBitmap(bitmap);

        final ViewHolder finalViewHolder = viewHolder;
        SimpleTarget<Bitmap> target=new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                finalViewHolder.iv_icon.setImageBitmap(resource);
            }
        };
        Glide.with(getContext()).load(list.get(i).getIconId()).asBitmap().override(380,380).into(target);


        return view;
    }

    class ViewHolder {
        ImageView iv_icon;
        TextView tv_title;
    }
}
