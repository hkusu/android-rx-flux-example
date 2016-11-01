package io.github.hkusu.rxflux.lib.flux;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public abstract class ActionCreator {
    private final Dispatcher dispatcher;

    protected ActionCreator(@NonNull Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    protected final <T1, T2> void dispatch(@NonNull T1 key, @Nullable T2 value) {
        dispatch(key, value, NotifyStoreChanged.TRUE);
    }

    protected final <T1, T2> void dispatch(@NonNull T1 key, @Nullable T2 value, @NonNull NotifyStoreChanged notifyStoreChanged) {
        this.dispatcher.getSubject().onNext(new Action<>(key, value, notifyStoreChanged));
    }
}
