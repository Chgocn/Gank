package com.chgocn.gankio.mvp.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

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
    protected void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

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