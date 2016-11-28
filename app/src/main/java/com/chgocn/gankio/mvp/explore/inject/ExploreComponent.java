package com.chgocn.gankio.mvp.explore.inject;

import com.chgocn.gankio.mvp.ApplicationModule;
import com.chgocn.gankio.mvp.explore.view.fragment.ExploreFragment;

import dagger.Component;

/**
 * Created by chgocn.
 */

@Component(modules = {ExploreModule.class , ApplicationModule.class})
public interface ExploreComponent {
    //LoginPresenter loginPresenter();
    void inject(ExploreFragment exploreFragment);

}
