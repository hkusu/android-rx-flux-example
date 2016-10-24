package io.github.hkusu.rxflux.lib.flux;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class Action<T> {
    public final String key;
    public final T value;
    final NotifyStoreChanged notifyStoreChanged;

    public enum NotifyStoreChanged {
        TRUE,
        FALSE,
    }

    Action(@NonNull String key, @Nullable T t, @NonNull NotifyStoreChanged notifyStoreChanged) {
        this.key = key;
        this.value = t;
        this.notifyStoreChanged = notifyStoreChanged;
    }
}
