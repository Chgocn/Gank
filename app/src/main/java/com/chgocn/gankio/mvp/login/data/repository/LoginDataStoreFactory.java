package com.chgocn.gankio.mvp.login.data.repository;

import com.chgocn.gankio.mvp.login.data.api.LoginService;
import com.chgocn.gankio.mvp.login.data.cache.LoginCache;
import com.chgocn.gankio.mvp.login.data.repository.datasource.CloudLoginDataSource;
import com.chgocn.gankio.mvp.login.data.repository.datasource.DiskLoginDataSource;

import javax.inject.Inject;

/**
 * Created by chgocn.
 */
public class LoginDataStoreFactory {

    @Inject
    LoginService mService;
    @Inject
    LoginCache mCache;

    @Inject
    public LoginDataStoreFactory() {

    }

    public CloudLoginDataSource getCloudDataSource() {
        return new CloudLoginDataSource(mService);
    }

    public DiskLoginDataSource getDiskDataSource() {
        return new DiskLoginDataSource(mCache);
    }

}