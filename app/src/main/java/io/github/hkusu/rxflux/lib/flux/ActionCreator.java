package io.github.hkusu.rxflux.lib.flux;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public abstract class ActionCreator {
    private final Dispatcher dispatcher;

    protected ActionCreator(@NonNull Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    protected final void post(@NonNull String key, @Nullable Object value) {
        post(key, value, Action.PostStoreChange.TRUE);
    }

    protected final void post(@NonNull String key, @Nullable Object value, @NonNull Action.PostStoreChange postStoreChange) {
        this.dispatcher.getSubject().onNext(new Action(key, value, postStoreChange));
    }
}
