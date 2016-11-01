package io.github.hkusu.rxflux.lib.flux;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public abstract class ActionCreator {
    private final Dispatcher dispatcher;

    protected ActionCreator(@NonNull Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    protected final <T> void dispatch(@NonNull Action.Key key, @Nullable T value) {
        this.dispatcher.getSubject().onNext(new Action<>(key, value, true));
    }

    protected final <T> void dispatchSkipNotify(@NonNull Action.Key key, @Nullable T value) {
        this.dispatcher.getSubject().onNext(new Action<>(key, value, false));
    }
}
