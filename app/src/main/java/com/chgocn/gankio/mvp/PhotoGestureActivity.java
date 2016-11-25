package com.chgocn.gankio.mvp;

import com.chgocn.lib.activity.BaseActivity;
import com.chgocn.lib.presenter.Presenter;

/**
 * Created by chgocn.
 */

public class PhotoGestureActivity extends BaseActivity{
    @Override
    public void initInjector() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_phote_scale;
    }

    @Override
    public Presenter getPresenter() {
        return null;
    }
}
