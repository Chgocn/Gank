package com.chgocn.gankio.mvp.login.data.repository.datasource;

import com.chgocn.gankio.mvp.login.data.api.LoginService;
import com.chgocn.gankio.mvp.login.domain.AuthAccount;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by chgocn.
 */
public class CloudLoginDataSource {
    LoginService mService;

    @Inject
    public CloudLoginDataSource(LoginService service) {
        this.mService = service;
    }

    // TODO: Add actions to connect with mService
    public Observable<AuthAccount> userLogin(String phone, String pwd) {
        return mService.userLogin(phone,pwd);
    }

}