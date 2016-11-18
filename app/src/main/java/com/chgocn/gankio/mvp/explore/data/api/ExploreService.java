package com.chgocn.gankio.mvp.explore.data.api;

import com.chgocn.gankio.mvp.main.data.api.BaseService;
import com.chgocn.gankio.mvp.main.domain.Gank;
import com.chgocn.gankio.mvp.main.domain.ResponseJson;

import java.util.List;

import javax.inject.Inject;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by chgocn.
 */
public class ExploreService extends BaseService {

    private Service mService;

    @Inject
    public ExploreService() {
        this.mService = getAdapter().create(Service.class);
    }

    // TODO: Create methods to conect with API

    public Observable<List<Gank>> getRandomGank(){
        return mService.getRandomGank().map(new ResponseFunc<>());
    }

    private interface Service {
        // TODO: Complete with calls
        @GET("random/data/Android/20")
        Observable<ResponseJson<List<Gank>>> getRandomGank();
    }

}