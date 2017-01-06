package com.chgocn.gankio.mvp.personal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.RelativeLayout;

import com.chgocn.gankio.mvp.R;
import com.chgocn.lib.fragment.BaseFragment;
import com.chgocn.lib.presenter.Presenter;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by chgocn.
 */
public class PersonalFragment extends BaseFragment implements PersonalPresenter.View {

    @Inject
    PersonalPresenter mPresenter;

    @BindView(R.id.rl_item)
    RelativeLayout relativeLayout;

    @Override
    public void initInjector() {

    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_personal;
    }

    @Override
    public Presenter getPresenter() {
        return null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search, menu);
    }
}