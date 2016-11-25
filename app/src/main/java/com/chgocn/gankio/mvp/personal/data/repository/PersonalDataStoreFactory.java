package com.chgocn.gankio.mvp.personal.data.repository;

import com.chgocn.gankio.mvp.personal.data.api.PersonalService;
import com.chgocn.gankio.mvp.personal.data.cache.PersonalCache;
import com.chgocn.gankio.mvp.personal.data.repository.datasource.CloudPersonalDataSource;
import com.chgocn.gankio.mvp.personal.data.repository.datasource.DiskPersonalDataSource;

import javax.inject.Inject;

/**
 * Created by chgocn.
 */
public class PersonalDataStoreFactory {

    @Inject
    PersonalService mService;
    @Inject
    PersonalCache mCache;

    @Inject
    public PersonalDataStoreFactory() {

    }

    public CloudPersonalDataSource getCloudDataSource() {
        return new CloudPersonalDataSource(mService);
    }

    public DiskPersonalDataSource getDiskDataSource() {
        return new DiskPersonalDataSource(mCache);
    }

}