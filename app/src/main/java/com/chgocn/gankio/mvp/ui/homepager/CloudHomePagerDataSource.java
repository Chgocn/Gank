package com.chgocn.gankio.mvp.ui.homepager;

import com.chgocn.gankio.mvp.model.Gank;
import com.chgocn.gankio.mvp.network.service.home.HomePagerService;

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