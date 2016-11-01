package io.github.hkusu.rxflux.lib.flux;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class Action<T> {
    public final Key key;
    public final T value;
    final boolean notifyStoreChanged;

    public interface Key {}

    Action(@NonNull Key key, @Nullable T t, @NonNull boolean notifyStoreChanged) {
        this.key = key;
        this.value = t;
        this.notifyStoreChanged = notifyStoreChanged;
    }
}
