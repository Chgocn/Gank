package com.chgocn.gankio.mvp.ui.personal.info;


import android.app.Activity;
import android.support.v4.app.FragmentActivity;

import com.chgocn.gankio.mvp.R;
import com.chgocn.lib.adapter.BaseRecyclerAdapter;
import com.chgocn.lib.view.ViewPager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chgocn.
 */

public class InfoEditRecyclerAdapter extends BaseRecyclerAdapter<PersonalInfoItem> {

    //一共3种布局
    public static final int TYPE_ONE = 1;//editText布局
    public static final int TYPE_TWO = 2;//textView布局
    private InfoEditPagerAdapter adapter;
    Map<Integer, Integer> map = new HashMap();

    public InfoEditRecyclerAdapter(Activity activity, List<PersonalInfoItem> items) {
        super(items);
        adapter = new InfoEditPagerAdapter((FragmentActivity) activity);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        if (getItemViewType(position) == TYPE_ONE) {
            viewHolder.setText(R.id.edit, "edit" + position);
            ViewPager viewPager = viewHolder.getView(R.id.vp_pages);
            viewPager.setAdapter(adapter);
            viewPager.setOffscreenPageLimit(5);

        } else {
            viewHolder.setText(R.id.text, "textview" + position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getViewType();
    }

    @Override
    public int getLayoutId(int viewType) {
        if (viewType == TYPE_ONE) {
            return R.layout.item_edit;
        } else {
            return R.layout.item_textview;
        }

    }
}
