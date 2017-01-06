package com.chgocn.gankio.mvp.explore;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chgocn.gankio.mvp.R;
import com.chgocn.gankio.mvp.domain.Gank;

import java.util.List;

/**
 * Created by chgocn.
 */

public class ExploreRecyclerAdapter extends BaseQuickAdapter<Gank,BaseViewHolder>{

    public ExploreRecyclerAdapter(int layoutResId, List<Gank> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, Gank gank) {
        ImageView imageView =  holder.getView(R.id.imageView);
        if (gank.getImages() != null && gank.getImages().size() > 0) {
            if (gank.getImages().get(0).contains("gif") || gank.getImages().get(0).contains("GIF")) {
                Glide.with(mContext).load(gank.getImages().get(0)).asGif().into(imageView);
            } else {
                Glide.with(mContext).load(gank.getImages().get(0)).into(imageView);
            }

        }
        holder.setText(R.id.title, gank.getDesc());
        holder.setText(R.id.who, gank.getWho());
        holder.setText(R.id.time, gank.getCreatedAt());
    }
}
