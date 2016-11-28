package com.chgocn.gankio.mvp.homepager.view.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chgocn.gankio.mvp.R;
import com.chgocn.gankio.mvp.main.domain.Gank;

import java.util.List;

/**
 * Created by chgocn.
 */

public class HomePagerRecyclerAdapter extends BaseQuickAdapter<Gank,BaseViewHolder>{

    public HomePagerRecyclerAdapter(int layoutResId, List<Gank> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, Gank gank) {
        ImageView img = holder.getView(R.id.middle_img);
        ImageView thumbnail = holder.getView(R.id.article_thumbnail);
        LinearLayout article = holder.getView(R.id.article);
        if (gank.getType().equals("福利")) {
            article.setVisibility(View.GONE);
            img.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(gank.getUrl()).into(img);
        } else {
            article.setVisibility(View.VISIBLE);
            img.setVisibility(View.GONE);
            if (gank.getImages() != null){
                if (gank.getImages().get(0).contains("gif") || gank.getImages().get(0).contains("GIF")) {
                    Glide.with(mContext).load(gank.getImages().get(0)).asGif().into(thumbnail);
                } else {
                    Glide.with(mContext).load(gank.getImages().get(0)).into(thumbnail);
                }
            }
            holder.setText(R.id.desc,gank.getDesc());
        }
        holder.setText(R.id.createdAt,gank.getCreatedAt());
        holder.setText(R.id.who,gank.getWho());
        holder.setText(R.id.desc,gank.getDesc());
        holder.setText(R.id.category,gank.getType());
    }
}
