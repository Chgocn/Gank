package com.chgocn.gankio.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chgocn.gankio.mvp.main.domain.Gank;
import com.chgocn.gankio.mvp.util.Intents;
import com.chgocn.lib.activity.BaseActivity;
import com.chgocn.lib.presenter.Presenter;

import butterknife.BindView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by chgocn.
 */

public class PhotoGestureActivity extends BaseActivity{

    @BindView(R.id.imageView)
    ImageView imageView;
    private PhotoViewAttacher attacher;

    public static Intent createIntent() {
        return new Intents.Builder("PHOTO_GESTURE").toIntent();
    }

    @Override
    public void initInjector() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_phote_gesture;
    }

    @Override
    public Presenter getPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Gank gank = getIntent().getParcelableExtra("gank");
        Glide.with(this).load(gank.getUrl()).into(imageView) ;
        attacher = new PhotoViewAttacher(imageView);
    }
}
