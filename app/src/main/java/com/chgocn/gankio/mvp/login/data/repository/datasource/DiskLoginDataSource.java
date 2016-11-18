package com.chgocn.gankio.mvp.login.data.repository.datasource;

import com.chgocn.gankio.mvp.login.data.cache.LoginCache;
import com.chgocn.gankio.mvp.main.data.api.BaseService;

import javax.inject.Inject;

/**
 * Created by chgocn.
 */
public class DiskLoginDataSource extends BaseService {

    LoginCache mCache;

    @Inject
    public DiskLoginDataSource(LoginCache cache) {
        this.mCache = cache;
    }

    // TODO: Add methods to connect with mCache
}