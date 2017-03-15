package com.chgocn.gankio.mvp.injector.component;

import com.chgocn.gankio.mvp.ui.homepager.HomePagerFragment;
import com.chgocn.gankio.mvp.injector.module.HomePagerModule;

import dagger.Component;

/**
 * Created by chgocn.
 */

@Component(modules = HomePagerModule.class)
public interface HomePagerComponent {
    //LoginPresenter loginPresenter();
    void inject(HomePagerFragment pagerFragment);

}
