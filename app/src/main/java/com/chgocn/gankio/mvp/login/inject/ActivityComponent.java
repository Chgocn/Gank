package com.chgocn.gankio.mvp.login.inject;

import com.chgocn.gankio.mvp.login.view.activity.LoginActivity;

import dagger.Component;

/**
 * Created by chgocn.
 */

@Component(modules = ActivityModule.class)
public interface ActivityComponent {
    //LoginPresenter loginPresenter();
    void inject(LoginActivity loginActivity);

}
