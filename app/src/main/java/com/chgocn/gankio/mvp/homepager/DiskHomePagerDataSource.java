package com.chgocn.gankio.mvp.homepager;

import com.chgocn.gankio.mvp.network.BaseService;

import javax.inject.Inject;

/**
 * Created by chgocn.
 */
public class DiskHomePagerDataSource extends BaseService {

    HomePagerCache mCache;

    @Inject
    public DiskHomePagerDataSource(HomePagerCache cache) {
        this.mCache = cache;
    }

    // TODO: Add methods to connect with mCache
}