package com.chgocn.gankio.mvp.home.data.repository.datasource;

import com.chgocn.gankio.mvp.home.data.api.HomeService;

import javax.inject.Inject;

import rx.Observable;

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