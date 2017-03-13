package com.chgocn.gankio.mvp.ui.homepager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chgocn.gankio.mvp.R;
import com.chgocn.gankio.mvp.model.Gank;

import java.util.List;

/**
 * Created by chgocn.
 */

public class HomePagerListAdapter extends RecyclerView.Adapter<HomePagerListAdapter.ViewHolder>{

    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private List<Gank> gankList;

    public HomePagerListAdapter(Context context,List<Gank> gankList) {
        mContext = context;
        this.gankList = gankList;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.item_home_pager, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Gank gank = gankList.get(position);

        if (gank.getType().equals("福利")) {
            holder.article.setVisibility(View.GONE);
            holder.img.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(gank.getUrl()).into(holder.img);
        } else {
            holder.article.setVisibility(View.VISIBLE);
            holder.img.setVisibility(View.GONE);
            if (gank.getImages() != null){
                Glide.with(mContext).load(gank.getImages().get(0)).into(holder.thumbnail);
            }
            holder.desc.setText(gank.getDesc());
        }
        holder.createdAt.setText(gank.getCreatedAt());
        holder.who.setText(gank.getWho());
        holder.desc.setText(gank.getDesc());
        holder.category.setText(gank.getType());

    }

    @Override
    public int getItemCount() {
        return gankList == null ? 0 : gankList.size();
    }

    public void replaceList(List<Gank> gankList) {
        this.gankList = gankList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView createdAt;
        TextView who;
        TextView desc;
        TextView category;
        ImageView img;
        LinearLayout article;
        ImageView avatar;
        ImageView thumbnail;

        public ViewHolder(View itemView) {
            super(itemView);
            createdAt = (TextView) itemView.findViewById(R.id.createdAt);
            category = (TextView) itemView.findViewById(R.id.category);
            who = (TextView) itemView.findViewById(R.id.who);
            desc = (TextView) itemView.findViewById(R.id.desc);
            img = (ImageView) itemView.findViewById(R.id.middle_img);
            avatar = (ImageView) itemView.findViewById(R.id.avatar);
            thumbnail = (ImageView) itemView.findViewById(R.id.article_thumbnail);
            article = (LinearLayout) itemView.findViewById(R.id.article);
        }
    }
}
