package com.chgocn.gankio.mvp.homepager.inject;

import com.chgocn.gankio.mvp.homepager.data.api.HomePagerService;
import com.chgocn.gankio.mvp.homepager.data.cache.HomePagerCache;
import com.chgocn.gankio.mvp.homepager.view.fragment.HomePagerFragment;

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
