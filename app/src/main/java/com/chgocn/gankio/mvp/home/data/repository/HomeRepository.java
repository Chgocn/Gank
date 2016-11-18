package com.chgocn.gankio.mvp.home.data.repository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by chgocn.
 */
public class HomeRepository {

    @Inject
    HomeDataStoreFactory mFactory;

    @Inject
    public HomeRepository() {
    }

    // TODO: Add methods to connect with mFactory
}