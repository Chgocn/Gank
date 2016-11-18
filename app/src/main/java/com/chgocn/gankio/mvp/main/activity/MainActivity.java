package com.chgocn.gankio.mvp.main.activity;

import android.os.Bundle;

import com.chgocn.gankio.mvp.R;
import com.chgocn.gankio.mvp.main.adapter.MainFragmentAdapter;
import com.chgocn.gankio.mvp.main.inject.DaggerMainComponent;
import com.chgocn.gankio.mvp.main.inject.MainModule;
import com.chgocn.gankio.mvp.main.presenter.HomePresenter;
import com.chgocn.lib.activity.TabPagerActivity;
import com.chgocn.lib.presenter.Presenter;

import javax.inject.Inject;

/**
 * Created by chgocn.
 */
public class MainActivity extends TabPagerActivity<MainFragmentAdapter> implements HomePresenter.View {

    @Inject
    public HomePresenter presenter;

    private int currentPosition = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.create();
        configureTabPager();
        int defaultPosition = 0;
        onPageSelected(defaultPosition);
    }

    @Override
    public void initInjector() {
        DaggerMainComponent.builder().mainModule(new MainModule()).build().inject(this);
    }

    @Override
    protected MainFragmentAdapter createAdapter() {
        return new MainFragmentAdapter(this);
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
        currentPosition = position;
        switch (position) {
            case 0:
                //setTitle(getResources().getString(R.string.home));
                break;
            case 1:

                //showRunFragment();
                break;
            case 2:

                //setTitle(getResources().getString(R.string.perm_identity));
                break;
            default:
                break;
        }
    }

    @Override
    public int getContentView() {
        return R.layout.activity_home;
    }

    @Override
    public Presenter getPresenter() {
        return presenter;
    }
}