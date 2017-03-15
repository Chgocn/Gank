package com.chgocn.gankio.mvp.injector.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chgocn.
 */
@Module
public class ApplicationModule {

    private final Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides
    Context provideContext(){
        return context;
    }
}
