package com.chgocn.gankio.mvp.ui.explore;

import com.chgocn.gankio.mvp.network.service.client.BaseService;

import javax.inject.Inject;

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