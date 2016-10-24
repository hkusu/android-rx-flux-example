package io.github.hkusu.rxflux.lib.flux;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class Action {
    public final String key;
    public final Object value;
    final NotifyStoreChanged notifyStoreChanged;

    public enum NotifyStoreChanged {
        TRUE,
        FALSE,
    }

    Action(@NonNull String key, @Nullable Object value, @NonNull NotifyStoreChanged notifyStoreChanged) {
        this.key = key;
        this.value = value;
        this.notifyStoreChanged = notifyStoreChanged;
    }
}
