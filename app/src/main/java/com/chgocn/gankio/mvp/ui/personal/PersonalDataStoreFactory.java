package com.chgocn.gankio.mvp.ui.personal;

import com.chgocn.gankio.mvp.network.service.personal.PersonalService;

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