package io.github.hkusu.rxflux.lib.flux;

import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

public final class Dispatcher {
    private final Subject<Action, Action> subject = new SerializedSubject<>(PublishSubject.create());

    public Dispatcher() {
    }

    final Subject<Action, Action> getSubject() {
        return subject;
    }
}
