package com.chgocn.gankio.mvp.personal.info;

import com.chgocn.lib.presenter.PresenterImpl;

import javax.inject.Inject;

/**
 * Created by chgocn.
 */

public class PersonalInfoEditPresenter extends PresenterImpl<PersonalInfoEditPresenter.View> {

    private PersonalInfoEditPresenter.View view;

    @Inject
    public PersonalInfoEditPresenter() {

    }

    @Override
    public void setView(PersonalInfoEditPresenter.View view) {
        this.view = view;
    }

    public interface View {
        //TODO: Create methods to implements in Activity or Fragment
    }


}
