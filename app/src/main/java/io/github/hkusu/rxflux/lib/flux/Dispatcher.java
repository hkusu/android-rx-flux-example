package io.github.hkusu.rxflux.lib.flux;

import android.support.annotation.NonNull;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

public final class Dispatcher {
    private final Subject<Action, Action> subject = new SerializedSubject<>(PublishSubject.create());

    public Dispatcher() {
    }

    final Observable<Action> observable() {
        return subject;
    }

    final void dispatch(@NonNull Action action) {
        subject.onNext(action);
    }
}
