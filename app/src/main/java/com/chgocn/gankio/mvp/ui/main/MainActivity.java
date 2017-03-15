package com.chgocn.gankio.mvp.ui.main;

import android.os.Bundle;
import android.view.View;

import com.chgocn.gankio.mvp.R;
import com.chgocn.lib.activity.TabPagerActivity;
import com.chgocn.lib.presenter.Presenter;

/**
 * Created by chgocn.
 */
public class MainActivity extends TabPagerActivity<MainFragmentAdapter> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureTabPager();
        int defaultPosition = 0;
        onPageSelected(defaultPosition);
    }

    @Override
    public Presenter getPresenter() {
        return null;
    }

    @Override
    public void initInjector() {

    }

    @Override
    protected MainFragmentAdapter createAdapter() {
        return new MainFragmentAdapter(this);
    }

    @Override
    public boolean isShowHomeAsUp() {
        return false;
    }

    @Override
    protected int getIcon(int position) {
        switch (position) {
            case 0:
                return R.drawable.tab_home_selector;
            case 1:
                return R.drawable.tab_explore_selector;
            case 2:
                return R.drawable.tab_perm_identity_selector;
            default:
                return super.getIcon(position);
        }
    }

    @Override
    protected void setCurrentItem(int position) {
        super.setCurrentItem(position);
        switch (position) {
            case 0:
                toolbar.setVisibility(View.GONE);
                break;
            case 1:
                toolbar.setVisibility(View.VISIBLE);
                setTitle("Explore");
                break;
            case 2:
                toolbar.setVisibility(View.VISIBLE);
                setTitle("Personal");
                break;
            default:
                break;
        }
    }

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }
}