package com.chgocn.gankio.mvp.homepager.data.repository;

import com.chgocn.gankio.mvp.homepager.data.api.HomePagerService;
import com.chgocn.gankio.mvp.homepager.data.cache.HomePagerCache;
import com.chgocn.gankio.mvp.homepager.data.repository.datasource.CloudHomePagerDataSource;
import com.chgocn.gankio.mvp.homepager.data.repository.datasource.DiskHomePagerDataSource;

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