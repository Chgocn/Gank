package com.chgocn.gankio.mvp.ui.homepager;

import dagger.Component;

/**
 * Created by chgocn.
 */

@Component(modules = HomePagerModule.class)
public interface HomePagerComponent {
    //LoginPresenter loginPresenter();
    void inject(HomePagerFragment pagerFragment);

}
