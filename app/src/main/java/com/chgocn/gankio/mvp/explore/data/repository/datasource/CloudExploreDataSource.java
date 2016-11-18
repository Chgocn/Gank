package com.chgocn.gankio.mvp.explore.data.repository.datasource;

import com.chgocn.gankio.mvp.explore.data.api.ExploreService;
import com.chgocn.gankio.mvp.main.domain.Gank;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by chgocn.
 */
public class CloudExploreDataSource {
    ExploreService mService;

    @Inject
    public CloudExploreDataSource(ExploreService service) {
        this.mService = service;
    }

    // TODO: Add actions to conect with mService

    public Observable<List<Gank>> getRandomGank(){
        return mService.getRandomGank();
    }
}