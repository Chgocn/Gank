package com.chgocn.gankio.mvp.personal;

import com.chgocn.gankio.mvp.network.BaseService;

import javax.inject.Inject;

/**
 * Created by chgocn.
 */
public class DiskPersonalDataSource extends BaseService {

    PersonalCache mCache;

    @Inject
    public DiskPersonalDataSource(PersonalCache cache) {
        this.mCache = cache;
    }

    // TODO: Add methods to connect with mCache
}