package com.chgocn.gankio.mvp.ui.home;

import com.chgocn.gankio.mvp.network.service.home.HomeService;

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