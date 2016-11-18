package com.chgocn.gankio.mvp.main.inject;

import com.chgocn.gankio.mvp.main.presenter.HomePresenter;

import dagger.Module;

/**
 * Created by chgocn.
 */
@Module
public class MainModule {
    HomePresenter provideHomePresenter(){
        return new HomePresenter();
    }
}
