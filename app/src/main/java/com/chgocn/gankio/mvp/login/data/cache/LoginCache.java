package com.chgocn.gankio.mvp.login.data.cache;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

/**
 * Created by chgocn.
 */
public class LoginCache {

    public static String PREFS_NAME = "project_name_prefs"; // TODO: Change name

    // TODO: Add your properties in Shared Preferences

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Inject
    public LoginCache(Context context) {
        this.sharedPreferences = context.getApplicationContext().getSharedPreferences(PREFS_NAME, Activity.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
    }

    // TODO: Add methods to get, set your properties

    public boolean clear() {
        this.editor.clear();
        return this.editor.commit();
    }


}