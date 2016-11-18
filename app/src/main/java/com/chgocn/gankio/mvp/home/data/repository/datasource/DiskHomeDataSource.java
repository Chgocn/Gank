package com.chgocn.gankio.mvp.home.data.repository.datasource;

import com.chgocn.gankio.mvp.main.data.api.BaseService;
import com.chgocn.gankio.mvp.home.data.cache.HomeCache;

import javax.inject.Inject;

import rx.Observable;

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