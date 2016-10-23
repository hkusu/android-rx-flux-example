package io.github.hkusu.rxflux.lib.rx;

import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

@Singleton
public class EventBus {
    private final Subject<Object, Object> subject = new SerializedSubject<>(PublishSubject.create());

    @Inject
    EventBus() {}

    @NonNull
    public <T> Subscription observe(@NonNull Class<T> clazz, @NonNull Scheduler scheduler, @NonNull Action1<T> action) {
        return subject
                .ofType(clazz)
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribe(action);
    }

    @NonNull
    public <T> Subscription observeOnMainThread(@NonNull Class<T> clazz, @NonNull Action1<T> action) {
        return observe(clazz, AndroidSchedulers.mainThread(), action);
    }

    @NonNull
    public <T> Subscription observeOnBackgroundThread(@NonNull Class<T> clazz, @NonNull Action1<T> action) {
        return observe(clazz, Schedulers.io(), action);
    }

    public void post(@NonNull Object object) {
        subject.onNext(object);
    }
}
