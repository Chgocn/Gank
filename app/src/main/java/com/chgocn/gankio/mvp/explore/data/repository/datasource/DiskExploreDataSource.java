package com.chgocn.gankio.mvp.explore.data.repository.datasource;

import com.chgocn.gankio.mvp.main.data.api.BaseService;
import com.chgocn.gankio.mvp.explore.data.cache.ExploreCache;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by chgocn.
 */
public class DiskExploreDataSource extends BaseService {

    ExploreCache mCache;

    @Inject
    public DiskExploreDataSource(ExploreCache cache) {
        this.mCache = cache;
    }

    // TODO: Add methods to connect with mCache
}