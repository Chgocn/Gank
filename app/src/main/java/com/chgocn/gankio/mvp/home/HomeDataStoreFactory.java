package com.chgocn.gankio.mvp.home;

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