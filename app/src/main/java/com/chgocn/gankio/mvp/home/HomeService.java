package com.chgocn.gankio.mvp.home;

import com.chgocn.gankio.mvp.network.BaseService;

import javax.inject.Inject;

/**
 * Created by chgocn.
 */
public class HomeService extends BaseService {

    private Service mService;

    @Inject
    public HomeService() {
        this.mService = getAdapter().create(Service.class);
    }

    // TODO: Create methods to conect with API 

    private interface Service {
        // TODO: Complete with calls
    }

}