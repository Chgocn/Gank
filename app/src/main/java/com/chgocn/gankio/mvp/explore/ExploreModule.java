package com.chgocn.gankio.mvp.explore;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chgocn.LoginActivity要获取到LoginPresenter的实例。
 */

@Module
public class ExploreModule {
    private ExploreFragment exploreFragment;

    public ExploreModule(ExploreFragment exploreFragment){
        this.exploreFragment = exploreFragment;
    }

    @Provides
    ExploreService provideExploreService() {
        return new ExploreService();
    }

    @Provides
    ExploreCache provideExploreCache(){
        return new ExploreCache(exploreFragment.getActivity());
    }

}