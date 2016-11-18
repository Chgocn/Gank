package com.chgocn.gankio.mvp.home.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.chgocn.gankio.mvp.home.view.adapter.HomePagerAdapter;
import com.chgocn.gankio.mvp.home.view.presenter.HomePresenter;
import com.chgocn.lib.fragment.TabPagerFragment;
import com.chgocn.lib.presenter.Presenter;

import javax.inject.Inject;

/**
 * Created by cn.
 */
public class HomeFragment extends TabPagerFragment<HomePagerAdapter> implements HomePresenter.View {

    @Inject
    HomePresenter mPresenter;

    @Override
    public void initInjector() {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        configureTabPager();
        pager.setOffscreenPageLimit(5);
    }

    @Override
    public Presenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected HomePagerAdapter createAdapter() {
        return new HomePagerAdapter(this);
    }
}