package com.chgocn.gankio.mvp.homepager.data.repository.datasource;

import com.chgocn.gankio.mvp.main.data.api.BaseService;
import com.chgocn.gankio.mvp.homepager.data.cache.HomePagerCache;

import javax.inject.Inject;

import rx.Observable;

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