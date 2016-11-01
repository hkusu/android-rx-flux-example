package io.github.hkusu.rxflux.lib.flux;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class Action<T1, T2> {
    public final T1 key;
    public final T2 value;
    final NotifyStoreChanged notifyStoreChanged;

    Action(@NonNull T1 key, @Nullable T2 t, @NonNull NotifyStoreChanged notifyStoreChanged) {
        this.key = key;
        this.value = t;
        this.notifyStoreChanged = notifyStoreChanged;
    }
}
