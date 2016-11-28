package com.chgocn.gankio.mvp.explore.view.presenter;


import android.content.Context;

import com.chgocn.gankio.mvp.WebActivity;
import com.chgocn.gankio.mvp.explore.data.repository.ExploreRepository;
import com.chgocn.gankio.mvp.main.domain.Gank;
import com.chgocn.gankio.mvp.main.domain.UseCase;
import com.chgocn.lib.presenter.PresenterImpl;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by chgocn.
 */
public class ExplorePresenter extends PresenterImpl<ExplorePresenter.View> {

    // TODO: Add your useCase
    @Inject
    public ExploreRepository repository;

    private View mView;

    @Inject
    Context context;

    @Inject
    public ExplorePresenter() {

    }

    @Override
    public void setView(View view) {
        this.mView = view;
    }


    public interface View {
        //TODO: Create methods to implements in Activity or Fragment
        void hideProgress();

        void replaceList(List<Gank> gankList);
    }

    public void fetchData() {
        new UseCase<List<Gank>>() {
            @Override
            public Observable buildUseCase() {
                return repository.getRandomGank();
            }
        }.execute(new Action1<List<Gank>>() {
            @Override
            public void call(List<Gank> ganks) {
                mView.hideProgress();
                mView.replaceList(ganks);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
                //Log.e(TAG,throwable.getMessage());
            }
        });
    }

    public void performOnListItemClick(Gank gank) {
        context.startActivity(WebActivity.createIntent().putExtra("gank",gank));
    }

}
