package com.chgocn.lib.presenter;

/**
 * Created by chgocn.
 */

public interface Presenter<T> {

    void setView(T view);

    void create();

    void resume();

    void fromBackground();

    void pause();

    void destroy();

    // TODO: Add your methods

}