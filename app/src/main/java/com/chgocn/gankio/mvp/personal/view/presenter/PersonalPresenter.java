package com.chgocn.gankio.mvp.personal.view.presenter;


import com.chgocn.lib.presenter.PresenterImpl;

import javax.inject.Inject;

/**
 * Created by chgocn.
 */
public class PersonalPresenter extends PresenterImpl<PersonalPresenter.View> {

    // TODO: Add your useCase

    private View mView;

    @Inject
    public PersonalPresenter() {
    }

    @Override
    public void setView(View view) {
        this.mView = view;
    }


    public interface View {
        //TODO: Create methods to implements in Activity or Fragment
    }

}
