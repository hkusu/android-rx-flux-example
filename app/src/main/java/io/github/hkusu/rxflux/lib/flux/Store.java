package io.github.hkusu.rxflux.lib.flux;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;
import rx.subscriptions.CompositeSubscription;

public abstract class Store implements Action.Key {
    private final Subject<Action, Action> storeSubject = new SerializedSubject<>(BehaviorSubject.create()); // Use BehaviorSubject to subscribe Store
    private final Dispatcher dispatcher;
    private final CompositeSubscription cs;

    protected Store(@NonNull Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
        cs = new CompositeSubscription();
    }

    private void observe(@NonNull Scheduler scheduler, @NonNull Action1<Action> rxAction) {
        cs.add(this.storeSubject
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribe(rxAction)
        );
    }

    // UI層(Activity/Fragment等)でのStore変更イベントの購読
    public final void observeOnMainThread(@NonNull Action1<Action> rxAction) {
        observe(AndroidSchedulers.mainThread(), rxAction);
    }

    // 非UI層(Activity/Fragment等)でのStore変更イベントの購読
    public final void observeOnBackgroundThread(@NonNull Action1<Action> rxAction) {
        observe(Schedulers.io(), rxAction);
    }

    // MEMO: 念のため外部からも叩けるようpublic
    public final void notifyStoreChanged(@Nullable Action fluxAction) {
        storeSubject.onNext(fluxAction);
    }

    // StoreでのDispatcherのsubject(アクションイベント)の購読
    // 現状ではunSubscribeしないもののとする(やろうと思えばActivityが破棄されててもStoreの更新が可能)
    // また現状ではStoreはシングルトンでActivityが破棄されても生存し続け再利用される
    // (辞めたい場合はDaggerの@Singletonアノテーションを外しCompositeSubscriptionで管理)
    protected final void on(@NonNull Action.Key key, @NonNull Action1<Action> rxAction) {
        dispatcher.on(key, rxAction)
                .subscribeOn(Schedulers.io())
                .subscribe(fluxAction -> {
                    if (fluxAction.notifyStoreChanged) {
                        // Storeの変更を通知
                        notifyStoreChanged(fluxAction);
                    }
                });
    }

    public final void clear() {
        if (cs.hasSubscriptions()) {
            cs.clear();
        }
    }

    // ２度とaddできなくなるので注意
    public final void unsubscribe() {
        if (!cs.isUnsubscribed()) {
            cs.unsubscribe();
        }
    }
}
