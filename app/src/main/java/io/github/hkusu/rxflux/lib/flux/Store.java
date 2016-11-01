package io.github.hkusu.rxflux.lib.flux;

import android.support.annotation.NonNull;

import java.util.Objects;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;
import rx.subscriptions.CompositeSubscription;

public abstract class Store implements Action.Key {
    private final Subject<Action, Action> storeSubject = new SerializedSubject<>(PublishSubject.create());
    private final Dispatcher dispatcher;
    private final CompositeSubscription cs;

    protected Store(@NonNull Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
        cs = new CompositeSubscription();
    }

    // UI層(Activity/Fragment等)でのStore(データ変更イベント)の購読
    public final void observeOnMainThread(@NonNull Action1<Action> rxAction) {
        cs.add(this.storeSubject
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) // Mainスレッドで購読
                .subscribe(rxAction)
        );
    }

    // StoreでのDispatcherのsubject(アクションイベント)の購読
    // 現状ではunSubscribeしないもののとする(やろうと思えばActivityが破棄されててもStoreの更新が可能)
    // また現状ではStoreはシングルトンでActivityが破棄されても生存し続け再利用される
    // (辞めたい場合はDaggerの@Singletonアノテーションを外しCompositeSubscriptionで管理)
    protected final void on(@NonNull Action.Key key, @NonNull Action1<Action> rxAction) {
        this.dispatcher.getSubject()
                .filter(fluxAction -> Objects.equals(fluxAction.key, key))
                .doOnNext(rxAction)
                .subscribeOn(Schedulers.io())
                .subscribe(fluxAction -> {
                    if (fluxAction.notifyStoreChanged) {
                        // Storeの変更を通知
                        storeSubject.onNext(fluxAction);
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
