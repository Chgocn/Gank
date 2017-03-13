package com.chgocn.gankio.mvp.ui.homepager;

import com.chgocn.gankio.mvp.network.service.home.HomePagerService;

import javax.inject.Inject;

/**
 * Created by chgocn.
 */
public class HomePagerDataStoreFactory {

    @Inject
    HomePagerService mService;
    @Inject
    HomePagerCache mCache;

    @Inject
    public HomePagerDataStoreFactory() {

    }

    public CloudHomePagerDataSource getCloudDataSource() {
        return new CloudHomePagerDataSource(mService);
    }

    public DiskHomePagerDataSource getDiskDataSource() {
        return new DiskHomePagerDataSource(mCache);
    }

}