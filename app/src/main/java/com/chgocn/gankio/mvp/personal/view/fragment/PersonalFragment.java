package com.chgocn.gankio.mvp.personal.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chgocn.gankio.mvp.R;

import com.chgocn.gankio.mvp.main.view.fragment.ParentFragment;
import com.chgocn.gankio.mvp.main.view.presenter.Presenter;

import com.chgocn.gankio.mvp.personal.view.presenter.PersonalPresenter;

/**
 * Created by chgocn.
 */
public class PersonalFragment extends ParentFragment implements PersonalPresenter.View {

    @Inject
    PersonalPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal, container, false);
        return view;
    }

    @Override
    public void initInjector() {

    }

    @Override
    public Presenter getPresenter() {
        return null;
    }

}