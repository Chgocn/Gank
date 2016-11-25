package com.chgocn.gankio.mvp.personal.data.api;

import com.chgocn.gankio.mvp.main.data.api.BaseService;

import javax.inject.Inject;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by chgocn.
 */
public class PersonalService extends BaseService {

    private Service mService;

    @Inject
    public PersonalService() {
        this.mService = getAdapter().create(Service.class);
    }

    // TODO: Create methods to conect with API 

    private interface Service {
        // TODO: Complete with calls
    }

}