package com.chgocn.gankio.mvp.homepager;

import android.util.Log;

import com.chgocn.gankio.mvp.domain.Gank;
import com.chgocn.gankio.mvp.domain.UseCase;
import com.chgocn.lib.presenter.PresenterImpl;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action1;


/**
 * Created by chgocn.
 */
public class HomePagerPresenter extends PresenterImpl<HomePagerPresenter.View> {

    private static final String TAG = "HomePagerPresenter";

    @Inject
    public HomePagerRepository repository;

    // TODO: Add your useCase

    private View mView;

    private int requestPage = 1;

    @Inject
    public HomePagerPresenter() {
    }

    @Override
    public void setView(View view) {
        this.mView = view;
    }


    public interface View {
        //TODO: Create methods to implements in Activity or Fragment
        void showMoreList(List<Gank> moreGankList);

        void replaceList(List<Gank> newGankList);

        void hideProgress();

        void onListItemClick(Gank gank);

    }

    public void performOnListItemClick(Gank gank) {
        mView.onListItemClick(gank);
    }

    private void fetchData(String category, int requestPage){
        new UseCase<List<Gank>>(){
            @Override
            public Observable buildUseCase() {
                return repository.getGanks(category,requestPage);
            }
        }.execute(new Action1<List<Gank>>() {
            @Override
            public void call(List<Gank> gankList) {
                if (requestPage == 1) {
                    mView.hideProgress();
                    mView.replaceList(gankList);
                } else {
                    mView.showMoreList(gankList);
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                mView.hideProgress();
                throwable.printStackTrace();
            }
        });
    }

    public void pullToRefresh(String category) {
        Log.e(TAG,"pullToRefresh()");
        requestPage = 1;
        fetchData(category,requestPage);
    }

    public void pullToLoadMore(String category) {
        Log.e(TAG,"pullToLoadMore()");
        requestPage++;
        fetchData(category,requestPage);
    }

}
