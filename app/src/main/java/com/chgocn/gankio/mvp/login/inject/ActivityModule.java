package com.chgocn.gankio.mvp.login.inject;

import com.chgocn.gankio.mvp.login.data.api.LoginService;
import com.chgocn.gankio.mvp.login.data.cache.LoginCache;
import com.chgocn.gankio.mvp.login.view.activity.LoginActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chgocn.LoginActivity要获取到LoginPresenter的实例。
 */

@Module
public class ActivityModule {
    private LoginActivity loginActivity;

    public ActivityModule(LoginActivity loginActivity){
        this.loginActivity = loginActivity;
    }

    @Provides
    LoginService provideLoginService() {
        return new LoginService();
    }

    @Provides
    LoginCache provideLoginCache(){
        return new LoginCache(loginActivity);
    }

}
