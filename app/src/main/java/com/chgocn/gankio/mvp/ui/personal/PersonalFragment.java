package com.chgocn.gankio.mvp.ui.personal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageView;

import com.chgocn.gankio.mvp.R;
import com.chgocn.lib.fragment.TabPagerFragment;
import com.chgocn.lib.presenter.Presenter;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by chgocn.
 */
public class PersonalFragment extends TabPagerFragment<PersonalPagerAdapter> implements PersonalPresenter.View {

    @Inject
    PersonalPresenter presenter;

    @BindView(R.id.banner)
    ImageView banner;

    @Override
    public void initInjector() {

    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        configureTabPager();
        slidingTabsLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                banner.setImageDrawable(getResources().getDrawable(R.drawable.spinner_inner));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected PersonalPagerAdapter createAdapter() {
        return new PersonalPagerAdapter(this);
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_personal;
    }

    @Override
    public Presenter getPresenter() {
        return presenter;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

}