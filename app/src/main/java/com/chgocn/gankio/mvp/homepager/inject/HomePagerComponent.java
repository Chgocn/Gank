package com.chgocn.gankio.mvp.homepager.inject;

import com.chgocn.gankio.mvp.homepager.view.fragment.HomePagerFragment;

import dagger.Component;

/**
 * Created by chgocn.
 */

@Component(modules = HomePagerModule.class)
public interface HomePagerComponent {
    //LoginPresenter loginPresenter();
    void inject(HomePagerFragment pagerFragment);

}
