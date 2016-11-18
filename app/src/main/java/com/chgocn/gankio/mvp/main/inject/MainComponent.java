package com.chgocn.gankio.mvp.main.inject;

import com.chgocn.gankio.mvp.main.activity.MainActivity;

import dagger.Component;

/**
 * Created by chgocn.
 */
@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
