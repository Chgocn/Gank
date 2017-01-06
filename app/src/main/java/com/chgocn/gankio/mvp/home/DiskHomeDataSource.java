package com.chgocn.gankio.mvp.home;

import com.chgocn.gankio.mvp.network.BaseService;

import javax.inject.Inject;

/**
 * Created by chgocn.
 */
public class DiskHomeDataSource extends BaseService {

    HomeCache mCache;

    @Inject
    public DiskHomeDataSource(HomeCache cache) {
        this.mCache = cache;
    }

    // TODO: Add methods to connect with mCache
}