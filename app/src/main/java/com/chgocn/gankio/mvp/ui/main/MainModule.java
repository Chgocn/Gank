package com.chgocn.gankio.mvp.ui.main;

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
