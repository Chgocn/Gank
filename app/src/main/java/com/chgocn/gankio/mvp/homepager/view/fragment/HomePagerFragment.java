package com.chgocn.gankio.mvp.homepager.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chgocn.gankio.mvp.R;
import com.chgocn.gankio.mvp.homepager.inject.DaggerHomePagerComponent;
import com.chgocn.gankio.mvp.homepager.inject.HomePagerModule;
import com.chgocn.gankio.mvp.homepager.view.adapter.HomePagerListAdapter;
import com.chgocn.gankio.mvp.homepager.view.presenter.HomePagerPresenter;
import com.chgocn.gankio.mvp.main.domain.Gank;
import com.chgocn.lib.fragment.BaseFragment;
import com.chgocn.lib.presenter.Presenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by chgocn.
 */
public class HomePagerFragment extends BaseFragment implements HomePagerPresenter.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.list)
    RecyclerView recyclerView;

    @Inject
    HomePagerPresenter mPresenter;

    HomePagerListAdapter adapter;

    private List<Gank> gankList;

    private String category;

    public static HomePagerFragment newInstance(String category) {
        Bundle args = new Bundle();
        args.putString("category",category);
        HomePagerFragment fragment = new HomePagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        category = getArguments().getString("category");
    }

    @Override
    public void initInjector() {
        DaggerHomePagerComponent.builder().homePagerModule(new HomePagerModule(this)).build().inject(this);
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_homepager;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        gankList = new ArrayList<>();
        adapter = new HomePagerListAdapter(getActivity(), gankList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setAdapter(adapter);
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
    public Presenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void showList() {

    }

    @Override
    public void replaceList(List<Gank> gankList) {
        adapter.replaceList(gankList);
    }

    @Override
    public void hideProgress() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        mPresenter.fetchData(category, 1);
    }
}