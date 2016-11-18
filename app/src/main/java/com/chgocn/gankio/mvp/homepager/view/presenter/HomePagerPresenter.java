package com.chgocn.gankio.mvp.homepager.view.presenter;

import com.chgocn.gankio.mvp.homepager.data.repository.HomePagerRepository;
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
public class HomePagerPresenter extends PresenterImpl<HomePagerPresenter.View> {

    private static final String TAG = "HomePagerPresenter";

    @Inject
    public HomePagerRepository repository;

    // TODO: Add your useCase

    private View mView;

    @Inject
    public HomePagerPresenter() {
    }

    @Override
    public void setView(View view) {
        this.mView = view;
    }


    public interface View {
        //TODO: Create methods to implements in Activity or Fragment
        void showList();

        void replaceList(List<Gank> gankList);

        void hideProgress();
    }

    public void fetchData(String category, int requestPage){
        new UseCase<List<Gank>>(){
            @Override
            public Observable buildUseCase() {
                return repository.getGanks(category,requestPage);
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

}
