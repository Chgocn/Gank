package com.chgocn.gankio.mvp.ui.explore;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chgocn.gankio.mvp.R;
import com.chgocn.gankio.mvp.model.Gank;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chgocn.
 */

public class ExploreListAdapter extends RecyclerView.Adapter<ExploreListAdapter.ViewHolder> {
    private Context context;
    private List<Gank> gankList;

    public ExploreListAdapter(Context context, List<Gank> gankList) {
        this.context = context;
        this.gankList = gankList;
    }

    @Override
    public ExploreListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_explore, parent, false));
    }

    @Override
    public void onBindViewHolder(ExploreListAdapter.ViewHolder holder, int position) {
        Gank gank = gankList.get(position);
        if (gank.getImages() != null && gank.getImages().size() > 0) {
            Glide.with(context).load(gank.getImages().get(0)).into(holder.imageView);
        }
        holder.title.setText(gank.getDesc());
        holder.who.setText(gank.getWho());
        holder.time.setText(gank.getCreatedAt());
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
        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.who) TextView who;
        @BindView(R.id.time) TextView time;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }


    }
}
