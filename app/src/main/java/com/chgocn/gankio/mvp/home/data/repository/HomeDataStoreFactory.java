package com.chgocn.gankio.mvp.home.data.repository;

import com.chgocn.gankio.mvp.home.data.api.HomeService;
import com.chgocn.gankio.mvp.home.data.cache.HomeCache;
import com.chgocn.gankio.mvp.home.data.repository.datasource.CloudHomeDataSource;
import com.chgocn.gankio.mvp.home.data.repository.datasource.DiskHomeDataSource;

import javax.inject.Inject;

/**
 * Created by chgocn.
 */
public class HomeDataStoreFactory {

    @Inject
    HomeService mService;
    @Inject
    HomeCache mCache;

    @Inject
    public HomeDataStoreFactory() {

    }

    public CloudHomeDataSource getCloudDataSource() {
        return new CloudHomeDataSource(mService);
    }

    public DiskHomeDataSource getDiskDataSource() {
        return new DiskHomeDataSource(mCache);
    }

}