package com.chgocn.gankio.mvp.ui.main;

import com.chgocn.lib.presenter.PresenterImpl;

import javax.inject.Inject;



/**
 * Created by chgocn.
 */
public class HomePresenter extends PresenterImpl<HomePresenter.View> {

    // TODO: Add your useCase

    private View mView;

    @Inject
    public HomePresenter() {
    }

    @Override
    public void setView(View view) {
        this.mView = view;
    }


    public interface View {
        //TODO: Create methods to implements in Activity or Fragment
    }

}
