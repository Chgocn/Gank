package com.chgocn.gankio.mvp.ui.homepager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chgocn.gankio.mvp.R;
import com.chgocn.gankio.mvp.injector.component.DaggerHomePagerComponent;
import com.chgocn.gankio.mvp.injector.module.HomePagerModule;
import com.chgocn.gankio.mvp.model.Gank;
import com.chgocn.gankio.mvp.ui.PhotoGestureActivity;
import com.chgocn.gankio.mvp.ui.WebActivity;
import com.chgocn.lib.fragment.BaseFragment;
import com.chgocn.lib.presenter.Presenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by chgocn.
 */
public class HomePagerFragment extends BaseFragment implements HomePagerPresenter.View{

    private static final String TAG = "HomePagerFragment";

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.list)
    RecyclerView recyclerView;

    @Inject
    HomePagerPresenter mPresenter;

    HomePagerRecyclerAdapter adapter;

    private List<Gank> gankList = new ArrayList<>();

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
    protected int getContentView() {
        return R.layout.fragment_homepager;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        adapter = new HomePagerRecyclerAdapter(R.layout.item_home_pager, gankList);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(adapter);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.pullToLoadMore(category);
            }
        });
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
            }
        });
        //
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.pullToRefresh(category);
            }
        });
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {

            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                mPresenter.performOnListItemClick(adapter.getData().get(i));
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mPresenter.pullToRefresh(category);
    }

    @Override
    public Presenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void showMoreList(List<Gank> moreGankList) {
        adapter.addData(moreGankList);
        adapter.loadMoreComplete();
    }

    @Override
    public void replaceList(List<Gank> newGankList) {
        adapter.setNewData(newGankList);
    }

    @Override
    public void hideProgress() {
        Log.e(TAG,"hideProgress()");
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void onListItemClick(Gank gank) {
        boolean isWelf = gank.getType().equals("福利");

        startActivity(isWelf ? PhotoGestureActivity.createIntent().putExtra("gank",gank) : WebActivity.createIntent().putExtra("gank",gank));
    }

}