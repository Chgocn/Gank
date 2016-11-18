package com.chgocn.gankio.mvp.login.data.repository;

import com.chgocn.gankio.mvp.login.domain.AuthAccount;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by chgocn.
 */
public class LoginRepository {

    @Inject
    LoginDataStoreFactory mFactory;

    @Inject
    public LoginRepository() {
    }

    // TODO: Add methods to connect with mFactory

    public Observable<AuthAccount> userLogin(String phone, String pwd){
        return mFactory.getCloudDataSource().userLogin(phone,pwd);
    }
}