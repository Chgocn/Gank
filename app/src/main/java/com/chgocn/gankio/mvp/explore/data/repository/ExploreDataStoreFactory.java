package com.chgocn.gankio.mvp.explore.data.repository;

import com.chgocn.gankio.mvp.explore.data.api.ExploreService;
import com.chgocn.gankio.mvp.explore.data.cache.ExploreCache;
import com.chgocn.gankio.mvp.explore.data.repository.datasource.CloudExploreDataSource;
import com.chgocn.gankio.mvp.explore.data.repository.datasource.DiskExploreDataSource;

import javax.inject.Inject;

/**
 * Created by chgocn.
 */
public class ExploreDataStoreFactory {

    @Inject
    ExploreService mService;
    @Inject
    ExploreCache mCache;

    @Inject
    public ExploreDataStoreFactory() {

    }

    public CloudExploreDataSource getCloudDataSource() {
        return new CloudExploreDataSource(mService);
    }

    public DiskExploreDataSource getDiskDataSource() {
        return new DiskExploreDataSource(mCache);
    }

}