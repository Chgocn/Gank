package com.chgocn.gankio.mvp.home;

import com.chgocn.gankio.mvp.home.HomeService;

import javax.inject.Inject;

/**
 * Created by chgocn.
 */
public class CloudHomeDataSource {
    HomeService mService;

    @Inject
    public CloudHomeDataSource(HomeService service) {
        this.mService = service;
    }

    // TODO: Add actions to conect with mService
}