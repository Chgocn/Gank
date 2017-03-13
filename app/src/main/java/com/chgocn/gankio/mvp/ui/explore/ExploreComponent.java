package com.chgocn.gankio.mvp.ui.explore;

import com.chgocn.gankio.mvp.ApplicationModule;

import dagger.Component;

/**
 * Created by chgocn.
 */

@Component(modules = {ExploreModule.class , ApplicationModule.class})
public interface ExploreComponent {
    //LoginPresenter loginPresenter();
    void inject(ExploreFragment exploreFragment);

}
