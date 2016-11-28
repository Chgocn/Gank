package com.chgocn.gankio.mvp.personal.data.repository.datasource;

import com.chgocn.gankio.mvp.main.data.api.BaseService;
import com.chgocn.gankio.mvp.personal.data.cache.PersonalCache;

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