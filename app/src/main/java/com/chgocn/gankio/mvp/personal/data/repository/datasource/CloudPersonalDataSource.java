package com.chgocn.gankio.mvp.personal.data.repository.datasource;

import com.chgocn.gankio.mvp.personal.data.api.PersonalService;

import javax.inject.Inject;

/**
 * Created by chgocn.
 */
public class CloudPersonalDataSource {
    PersonalService mService;

    @Inject
    public CloudPersonalDataSource(PersonalService service) {
        this.mService = service;
    }

    // TODO: Add actions to conect with mService
}