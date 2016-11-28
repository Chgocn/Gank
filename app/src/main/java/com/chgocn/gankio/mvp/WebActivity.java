package com.chgocn.gankio.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;

import com.chgocn.gankio.mvp.main.domain.Gank;
import com.chgocn.gankio.mvp.util.Intents;
import com.chgocn.gankio.mvp.view.ProgressWebView;
import com.chgocn.lib.activity.BaseActivity;
import com.chgocn.lib.presenter.Presenter;

import butterknife.BindView;

/**
 * Created by chgocn.
 */

public class WebActivity extends BaseActivity{

    @BindView(R.id.web_content)
    ProgressWebView webView;

    @Override
    public void initInjector() {

    }

    public static Intent createIntent() {
        return new Intents.Builder("WEB").toIntent();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_web;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Gank gank = getIntent().getParcelableExtra("gank");
        boolean isWelf = gank.getType().equals("福利");
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        //settings.setLoadWithOverviewMode(true);
        //settings.setAppCacheEnabled(true);
        //settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //settings.setSupportZoom(true);

        webView.loadUrl(!isWelf ? gank.getUrl() : "");
    }

    @Override
    public Presenter getPresenter() {
        return null;
    }
}
