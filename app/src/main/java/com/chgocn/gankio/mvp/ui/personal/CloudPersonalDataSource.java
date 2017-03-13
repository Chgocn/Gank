package com.chgocn.gankio.mvp.ui.personal;

import com.chgocn.gankio.mvp.network.service.personal.PersonalService;

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