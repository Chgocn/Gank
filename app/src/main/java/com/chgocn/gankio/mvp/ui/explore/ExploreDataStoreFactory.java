package com.chgocn.gankio.mvp.ui.explore;

import com.chgocn.gankio.mvp.network.service.explore.ExploreService;

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