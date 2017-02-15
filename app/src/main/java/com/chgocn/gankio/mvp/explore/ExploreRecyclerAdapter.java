package com.chgocn.gankio.mvp.explore;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chgocn.gankio.mvp.R;
import com.chgocn.gankio.mvp.WebActivity;
import com.chgocn.gankio.mvp.domain.Gank;
import com.chgocn.lib.adapter.BaseRecyclerAdapter;
import com.chgocn.lib.view.BannerPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chgocn.
 */

public class ExploreRecyclerAdapter extends BaseRecyclerAdapter<Gank> {

    public static final int TYPE_HEADER = 1;//editText布局
    public static final int TYPE_NORMAL = 2;//textView布局

    public ExploreRecyclerAdapter(Context context, List<Gank> gankList) {
        super(context);
        this.mItems = gankList;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else {
            return TYPE_NORMAL;
        }
    }

    @Override
    public int getLayoutId(int viewType) {
        if (viewType == TYPE_HEADER) {
            return R.layout.item_header_banner;
        } else {
            return R.layout.item_explore;
        }

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position == 0) {
            BannerPager bannerPager = holder.getView(R.id.bannerPager);
            bannerPager.setImageLoader(new BannerPager.ImageLoaderInterface() {
                @Override
                public void displayImage(Context context, Object path, View imageView) {
                    //Glide 加载图片简单用法
                    Glide.with(context).load(path).into((ImageView) imageView);
                }

                @Override
                public View createImageView(Context context) {
                    return null;
                }
            });
            //设置图片集合
            //资源文件
            List<Integer> images = new ArrayList<>();
            images.add(R.mipmap.ic_launcher);
            images.add(R.mipmap.ic_launcher);
            images.add(R.mipmap.ic_launcher);
            bannerPager.setImages(images);
            bannerPager.setOffscreenPageLimit(images.size());
            //banner设置方法全部调用完毕时最后调用
            bannerPager.start();
        } else {
            Gank gank = mItems.get(position - 1);
            ImageView imageView = holder.getView(R.id.imageView);
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
            holder.setOnItemClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(WebActivity.createIntent().putExtra("gank",gank));
                }
            });
        }

    }
}
