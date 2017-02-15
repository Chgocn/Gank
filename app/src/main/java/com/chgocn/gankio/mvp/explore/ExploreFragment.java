package com.chgocn.gankio.mvp.explore;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chgocn.gankio.mvp.ApplicationModule;
import com.chgocn.gankio.mvp.R;
import com.chgocn.gankio.mvp.domain.Gank;
import com.chgocn.lib.fragment.DialogFragment;
import com.chgocn.lib.presenter.Presenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by chgocn.
 */
public class ExploreFragment extends DialogFragment implements ExplorePresenter.View {

    @BindView(R.id.list)
    RecyclerView list;

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;

    @Inject
    ExplorePresenter mPresenter;

    private ExploreRecyclerAdapter adapter;

    private List<Gank> gankList = new ArrayList<>();

    @Override
    public void initInjector() {
        DaggerExploreComponent.builder().exploreModule(new ExploreModule(this)).applicationModule(new ApplicationModule(getContext())).build().inject(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mPresenter.fetchData();
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        adapter = new ExploreRecyclerAdapter(getActivity(), gankList);
        list.setLayoutManager(new LinearLayoutManager(activity));
        list.setAdapter(adapter);

//        list.addOnItemTouchListener(new OnItemClickListener() {
//            @Override
//            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
//                mPresenter.performOnListItemClick(adapter.getItem(i));
//            }
//        });
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
            }
        });
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.fetchData();
            }
        });

    }

    @Override
    protected int getContentView() {
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
        adapter.setItems(gankList);
    }
}