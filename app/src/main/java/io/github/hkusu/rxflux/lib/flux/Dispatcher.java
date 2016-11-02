package io.github.hkusu.rxflux.lib.flux;

import android.support.annotation.NonNull;

import java.util.Objects;

import rx.Observable;
import rx.functions.Action1;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

public final class Dispatcher {
    private final Subject<Action, Action> dispatcherSubject = new SerializedSubject<>(PublishSubject.create());

    public Dispatcher() {
    }

    final Observable<Action> on(@NonNull Action.Key key, @NonNull Action1<Action> rxAction) {
        return dispatcherSubject
                .filter(fluxAction -> Objects.equals(fluxAction.key, key))
                .doOnNext(rxAction);
    }

    final void dispatch(@NonNull Action fluxAction) {
        dispatcherSubject.onNext(fluxAction);
    }
}
