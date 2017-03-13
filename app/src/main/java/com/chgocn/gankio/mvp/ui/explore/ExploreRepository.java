package com.chgocn.gankio.mvp.ui.explore;

import com.chgocn.gankio.mvp.model.Gank;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by chgocn.
 */
public class ExploreRepository {

    @Inject
    ExploreDataStoreFactory mFactory;

    @Inject
    public ExploreRepository() {
    }

    // TODO: Add methods to connect with mFactory
    public Observable<List<Gank>> getRandomGank(){
        return mFactory.getCloudDataSource().getRandomGank();
    }
}