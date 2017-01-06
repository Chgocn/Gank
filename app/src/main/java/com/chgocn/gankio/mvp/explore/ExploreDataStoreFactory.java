package com.chgocn.gankio.mvp.explore;

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