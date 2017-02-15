package com.chgocn.lib.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.chgocn.lib.presenter.Presenter;
import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.RxLifecycle;
import com.trello.rxlifecycle.android.FragmentEvent;

import javax.annotation.Nonnull;

import butterknife.ButterKnife;
import rx.Observable;
import rx.subjects.BehaviorSubject;


/**
 * Created by chgocn.
 */
public abstract class BaseFragment extends Fragment implements LifecycleProvider<FragmentEvent> {

    private static final String TAG = "BaseFragment";
    protected Activity activity;

    private final BehaviorSubject<FragmentEvent> lifecycleSubject = BehaviorSubject.create();

    public void initPresenter() {
        initInjector();
        if (getPresenter() != null) {
            getPresenter().setView(this);
        }
    }

    @Nonnull
    @Override
    public Observable<FragmentEvent> lifecycle() {
        return lifecycleSubject.asObservable();
    }

    @Nonnull
    @Override
    public <T> LifecycleTransformer<T> bindUntilEvent(@Nonnull FragmentEvent event) {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
    }

    @Nonnull
    @Override
    public <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycle.bind(lifecycleSubject);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = getActivity();
        lifecycleSubject.onNext(FragmentEvent.ATTACH);
        Log.d(TAG, getClass().getSimpleName() + ".onAttach...");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifecycleSubject.onNext(FragmentEvent.CREATE);
        Log.d(TAG, getClass().getSimpleName() + ".onCreate...");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getContentView(), container, false);
        ButterKnife.bind(this, view);
        initView(savedInstanceState);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initPresenter();
        lifecycleSubject.onNext(FragmentEvent.CREATE_VIEW);
        initData(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        lifecycleSubject.onNext(FragmentEvent.START);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, getClass().getSimpleName() + ".onResume...");
        Presenter presenter = getPresenter();
        if (presenter != null) {
            presenter.resume();
        }
        lifecycleSubject.onNext(FragmentEvent.RESUME);
    }

    @Override
    public void onPause() {
        lifecycleSubject.onNext(FragmentEvent.PAUSE);
        super.onPause();
        Log.d(TAG, getClass().getSimpleName() + ".onPause...");
        Presenter presenter = getPresenter();
        if (presenter != null) {
            presenter.pause();
        }
    }

    @Override
    public void onStop() {
        lifecycleSubject.onNext(FragmentEvent.STOP);
        super.onStop();
        Log.d(TAG, getClass().getSimpleName() + ".onStop...");
    }

    @Override
    public void onDestroyView() {
        lifecycleSubject.onNext(FragmentEvent.DESTROY_VIEW);
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        lifecycleSubject.onNext(FragmentEvent.DESTROY);
        super.onDestroy();
        Log.d(TAG, getClass().getSimpleName() + ".onDestroy...");
        Presenter presenter = getPresenter();
        if (presenter != null) {
            presenter.destroy();
        }
    }

    @Override
    public void onDetach() {
        lifecycleSubject.onNext(FragmentEvent.DETACH);
        super.onDetach();
        Log.d(TAG, getClass().getSimpleName() + ".onDetach...");
    }

    public abstract void initInjector();

    protected abstract void initView(@Nullable Bundle savedInstanceState);

    protected abstract void initData(Bundle savedInstanceState);

    protected abstract int getContentView();

    public abstract Presenter getPresenter();

    public void hideKeyboard(Context context, IBinder windowToken) {
        InputMethodManager mgr = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(windowToken, 0);
    }
}
