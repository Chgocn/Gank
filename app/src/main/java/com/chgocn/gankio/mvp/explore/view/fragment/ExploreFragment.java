package com.chgocn.gankio.mvp.explore.view.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import com.chgocn.gankio.mvp.R;
import com.chgocn.gankio.mvp.explore.inject.DaggerExploreComponent;
import com.chgocn.gankio.mvp.explore.inject.ExploreModule;
import com.chgocn.gankio.mvp.explore.view.adapter.ExploreListAdapter;
import com.chgocn.gankio.mvp.explore.view.presenter.ExplorePresenter;
import com.chgocn.gankio.mvp.main.domain.Gank;
import com.chgocn.lib.fragment.DialogFragment;
import com.chgocn.lib.presenter.Presenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by chgocn.
 */
public class ExploreFragment extends DialogFragment implements ExplorePresenter.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.search)
    SearchView searchView;

    @BindView(R.id.list)
    RecyclerView list;

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;

    @Inject
    ExplorePresenter mPresenter;

    private ExploreListAdapter adapter;

    private List<Gank> gankList = new ArrayList<>();

    @Override
    public void initInjector() {
        DaggerExploreComponent.builder().exploreModule(new ExploreModule(this)).build().inject(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new ExploreListAdapter(getActivity(),gankList);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));
        list.setAdapter(adapter);
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
            }
        });
        //
        refreshLayout.setOnRefreshListener(this);
        onRefresh();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_explore;
    }

    @Override
    public Presenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void hideProgress() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void replaceList(List<Gank> gankList) {
        adapter.replaceList(gankList);
    }

    @Override
    public void onRefresh() {
        mPresenter.fetchData();
    }
}