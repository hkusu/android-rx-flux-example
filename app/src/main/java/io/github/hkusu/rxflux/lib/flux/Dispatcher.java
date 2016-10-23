package io.github.hkusu.rxflux.lib.flux;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

@Singleton
public class Dispatcher {
    private final Subject<Action, Action> subject = new SerializedSubject<>(PublishSubject.create());

    @Inject
    public Dispatcher() {
    }

    Subject<Action, Action> getSubject() {
        return subject;
    }
}
