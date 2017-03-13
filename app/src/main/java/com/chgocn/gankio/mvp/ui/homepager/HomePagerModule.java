package com.chgocn.gankio.mvp.ui.homepager;

import com.chgocn.gankio.mvp.network.service.home.HomePagerService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chgocn.LoginActivity要获取到LoginPresenter的实例。
 */

@Module
public class HomePagerModule {
    private HomePagerFragment pagerFragment;

    public HomePagerModule(HomePagerFragment pagerFragment){
        this.pagerFragment = pagerFragment;
    }

    @Provides
    HomePagerService provideHomePagerService() {
        return new HomePagerService();
    }

    @Provides
    HomePagerCache provideHomePagerCache(){
        return new HomePagerCache(pagerFragment.getActivity());
    }

}
