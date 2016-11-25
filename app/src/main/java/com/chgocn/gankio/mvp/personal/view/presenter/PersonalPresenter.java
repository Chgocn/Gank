package com.chgocn.gankio.mvp.personal.view.presenter;

import javax.inject.Inject;

import com.chgocn.gankio.mvp.main.view.presenter.PresenterImpl;

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
