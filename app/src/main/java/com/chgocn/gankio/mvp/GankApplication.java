package com.chgocn.gankio.mvp;

import com.chgocn.lib.BaseApplication;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * Created by chgocn.
 */

public class GankApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init("Logger")                 // default PRETTYLOGGER or use just init()
                .methodCount(3)                 // default 2
                .hideThreadInfo()               // default shown
                .logLevel(LogLevel.NONE)        // default LogLevel.FULL
                .methodOffset(2);               // default 0; //default AndroidLogAdapter
    }
}
