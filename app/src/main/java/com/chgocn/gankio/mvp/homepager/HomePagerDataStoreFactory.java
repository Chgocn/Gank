package com.chgocn.gankio.mvp.homepager;

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