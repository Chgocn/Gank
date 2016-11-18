package com.chgocn.gankio.mvp.homepager.data.repository;

import com.chgocn.gankio.mvp.main.domain.Gank;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by chgocn.
 */
public class HomePagerRepository {

    @Inject
    HomePagerDataStoreFactory mFactory;

    @Inject
    public HomePagerRepository() {
    }

    // TODO: Add methods to connect with mFactory

    public Observable<List<Gank>> getGanks(String category, int requestPage) {
        return mFactory.getCloudDataSource().getGanks(category,requestPage);
    }
}