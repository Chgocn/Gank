package com.chgocn.gankio.mvp.ui.main;

import dagger.Component;

/**
 * Created by chgocn.
 */
@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
