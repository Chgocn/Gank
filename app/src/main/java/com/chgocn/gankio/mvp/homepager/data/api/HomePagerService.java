package com.chgocn.gankio.mvp.homepager.data.api;

import com.chgocn.gankio.mvp.main.data.api.BaseService;
import com.chgocn.gankio.mvp.main.domain.Gank;
import com.chgocn.gankio.mvp.main.domain.ResponseJson;

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