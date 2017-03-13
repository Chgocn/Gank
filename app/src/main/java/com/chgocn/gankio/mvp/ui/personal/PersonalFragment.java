package com.chgocn.gankio.mvp.ui.personal;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.chgocn.gankio.mvp.R;
import com.chgocn.gankio.mvp.ui.personal.info.PersonalInfoEditActivity;
import com.chgocn.lib.fragment.BaseFragment;
import com.chgocn.lib.presenter.Presenter;
import com.chgocn.lib.view.BannerPager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by chgocn.
 */
public class PersonalFragment extends BaseFragment implements PersonalPresenter.View {

    @Inject
    PersonalPresenter mPresenter;

    @BindView(R.id.rl_avatar)
    RelativeLayout rlAvatar;
    @BindView(R.id.bannerPager)
    BannerPager bannerPager;

    @Override
    public void initInjector() {

    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        //设置图片加载器
        bannerPager.setImageLoader(new BannerPager.ImageLoaderInterface() {
            @Override
            public void displayImage(Context context, Object path, View imageView) {
                //Glide 加载图片简单用法
                Glide.with(context).load(path).into((ImageView) imageView);
            }

            @Override
            public View createImageView(Context context) {
                return null;
            }
        });
        //设置图片集合
        //资源文件
        List<Integer> images = new ArrayList<>();
        images.add(R.mipmap.ic_launcher);
        images.add(R.mipmap.ic_launcher);
        images.add(R.mipmap.ic_launcher);
        bannerPager.setImages(images);
        bannerPager.setOffscreenPageLimit(images.size());
        //banner设置方法全部调用完毕时最后调用
        bannerPager.start();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_personal;
    }

    @Override
    public Presenter getPresenter() {
        return null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search, menu);
    }

    @Override
    public void onStart() {
        super.onStart();
        bannerPager.startAutoPlay();
    }

    @Override
    public void onPause() {
        super.onPause();
        bannerPager.stopAutoPlay();
    }

    @OnClick(R.id.rl_avatar)
    public void onClick() {
        startActivity(PersonalInfoEditActivity.createIntent(getActivity()));
    }

}