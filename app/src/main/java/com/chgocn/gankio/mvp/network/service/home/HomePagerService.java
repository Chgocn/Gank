package com.chgocn.gankio.mvp.network.service.home;


import com.chgocn.gankio.mvp.model.Gank;
import com.chgocn.gankio.mvp.network.bean.response.ResponseJson;
import com.chgocn.gankio.mvp.network.service.client.BaseService;

import java.util.List;

import javax.inject.Inject;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by chgocn.
 */
public class HomePagerService extends BaseService {

    private Service mService;

    @Inject
    public HomePagerService() {
        this.mService = getAdapter().create(Service.class);
    }

    // TODO: Create methods to conect with API

    public Observable<List<Gank>> getGanks(String category, int requestPage) {
        return mService.getGanks(category,10,requestPage).map(new ResponseFunc<>());
    }

    private interface Service {
        // TODO: Complete with calls
        @GET("data/{category}/{requestCount}/{requestPage}")
        Observable<ResponseJson<List<Gank>>> getGanks(@Path("category") String category, @Path("requestCount") int requestCount, @Path("requestPage") int requestPage);
    }

}