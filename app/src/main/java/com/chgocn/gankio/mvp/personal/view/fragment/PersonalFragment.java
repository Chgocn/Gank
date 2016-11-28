package com.chgocn.gankio.mvp.personal.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.chgocn.gankio.mvp.R;
import com.chgocn.gankio.mvp.personal.view.presenter.PersonalPresenter;
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
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, "你点了我！！！！！", Toast.LENGTH_SHORT).show();
            }
        });
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

}