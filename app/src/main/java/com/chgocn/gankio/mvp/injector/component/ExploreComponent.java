package com.chgocn.gankio.mvp.injector.component;

import com.chgocn.gankio.mvp.injector.module.ApplicationModule;
import com.chgocn.gankio.mvp.injector.module.ExploreModule;
import com.chgocn.gankio.mvp.ui.explore.ExploreFragment;

import dagger.Component;

/**
 * Created by chgocn.
 */

@Component(modules = {ExploreModule.class , ApplicationModule.class})
public interface ExploreComponent {
    //LoginPresenter loginPresenter();
    void inject(ExploreFragment exploreFragment);

}
