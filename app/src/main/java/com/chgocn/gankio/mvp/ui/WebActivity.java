package com.chgocn.gankio.mvp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.webkit.WebSettings;

import com.chgocn.gankio.mvp.Intents;
import com.chgocn.gankio.mvp.R;
import com.chgocn.gankio.mvp.model.Gank;
import com.chgocn.lib.activity.BaseActivity;
import com.chgocn.lib.presenter.Presenter;

import butterknife.BindView;

/**
 * Created by chgocn.
 */

public class WebActivity extends BaseActivity{

    @BindView(R.id.web_content)
    ProgressWebView webView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

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

        setSupportActionBar(toolbar);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
