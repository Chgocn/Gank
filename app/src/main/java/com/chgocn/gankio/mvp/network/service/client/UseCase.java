package com.chgocn.gankio.mvp.network.service.client;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

/**
 * Created by chgocn.
 */
public abstract class UseCase<T> {

    private Action1 onNext;
    private Action1 onError;
    private Subscriber<T> subscriber;

    public void execute() {
        this.onNext = null;
        this.onError = null;

        Observable.defer(new Func0<Observable<Object>>() {
            @Override
            public Observable<Object> call() {
                return buildUseCase();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(printStackTrace)
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        getSubscriber();
                    }
                });

    }

    public void execute(Action1<T> onNextAction) {

        this.onNext = onNextAction;
        this.onError = null;

        Observable.defer(new Func0<Observable<T>>() {
            @Override
            public Observable<T> call() {
                return buildUseCase();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(printStackTrace)
                .subscribe(getSubscriber());

    }

    public void execute(Action1<T> onNextAction, Action1<Throwable> onErrorAction) {

        this.onNext = onNextAction;
        this.onError = onErrorAction;

        Observable.defer(new Func0<Observable<T>>() {
            @Override
            public Observable<T> call() {
                return buildUseCase();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(printStackTrace)
                .subscribe(getSubscriber());

    }


    Action1<Throwable> printStackTrace = new Action1<Throwable>() {
        @Override
        public void call(Throwable throwable) {
            throwable.printStackTrace();
        }
    };

    public abstract Observable buildUseCase();

    public Subscriber<T> getSubscriber() {

        if (subscriber != null && subscriber.isUnsubscribed()) {
            subscriber = null;
        }

        if (subscriber == null) {
            subscriber = new Subscriber<T>() {
                @Override
                public void onCompleted() {
                }

                @Override
                public void onError(Throwable e) {
                    if (onError != null) {
                        onError.call(e);
                    }
                }

                @Override
                public void onNext(T t) {
                    if (onNext != null) {
                        onNext.call(t);
                    }
                }
            };
        }

        return subscriber;
    }

    public void unsubscribe() {
        if (subscriber != null) {
            try {
                subscriber.unsubscribe();
            } catch (Exception ex) {
                // Can provoke IllegalStateException if you do realm.close of closed instance
                ex.printStackTrace();
            }
            subscriber = null;
        }
    }

    public void resetSubscription() {
        unsubscribe();

        if (onNext == null && onError == null) {
            execute();
        } else if (onNext != null && onError == null) {
            execute(onNext);
        } else if (onNext != null && onError != null) {
            execute(onNext, onError);
        }
    }

}
