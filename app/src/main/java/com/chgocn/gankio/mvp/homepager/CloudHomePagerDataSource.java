package com.chgocn.gankio.mvp.homepager;

import com.chgocn.gankio.mvp.domain.Gank;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by chgocn.
 */
public class CloudHomePagerDataSource {
    HomePagerService mService;

    @Inject
    public CloudHomePagerDataSource(HomePagerService service) {
        this.mService = service;
    }

    // TODO: Add actions to conect with mService

    public Observable<List<Gank>> getGanks(String category, int requestPage) {
        return mService.getGanks(category,requestPage);
    }
}