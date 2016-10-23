package io.github.hkusu.rxflux.lib.rx;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class SubscriptionManager {
    private final EventBus bus;
    private final CompositeSubscription cs;

    @Inject
    SubscriptionManager (@NonNull EventBus bus) {
        this.bus = bus;
        this.cs = new CompositeSubscription();
    }

    public <T> void observe(@NonNull Class<T> clazz, @NonNull Scheduler scheduler, @NonNull Action1<T> action) {
        cs.add(bus.observe(clazz, scheduler, action));
    }

    public <T> void observeOnMainThread(@NonNull Class<T> clazz, @NonNull Action1<T> action) {
        cs.add(bus.observeOnMainThread(clazz, action));
    }

    public <T> void observeOnBackgroundThread(@NonNull Class<T> clazz, @NonNull Action1<T> action) {
        cs.add(bus.observeOnBackgroundThread(clazz, action));
    }

    public <T> void observe(@NonNull Observable<T> observable, @NonNull Scheduler scheduler, @NonNull Action1<T> action) {
        cs.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribe(action)
        );
    }

    public <T> void observeOnMainThread(@NonNull Observable<T> observable, @NonNull Action1<T> action) {
        observe(observable, AndroidSchedulers.mainThread(), action);
    }

    public <T> void observeOnBackgroundThread(@NonNull Observable<T> observable, @NonNull Action1<T> action) {
        observe(observable, Schedulers.io(), action);
    }

    @NonNull
    public SubscriptionManager add(@NonNull Subscription subscription) {
        cs.add(subscription);
        return this;
    }

    public void clear() {
        if (cs.hasSubscriptions()) {
            cs.clear();
        }
    }

    public void unsubscribe() {
        if (!cs.isUnsubscribed()) {
            cs.unsubscribe();
        }
    }
}
