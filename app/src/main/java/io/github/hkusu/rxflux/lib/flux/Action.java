package io.github.hkusu.rxflux.lib.flux;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class Action {
    public final String key;
    public final Object value;
    final PostStoreChange postStoreChange;

    public enum PostStoreChange {
        TRUE,
        FALSE,
    }

    Action(@NonNull String key, @Nullable Object value, @NonNull PostStoreChange postStoreChange) {
        this.key = key;
        this.value = value;
        this.postStoreChange = postStoreChange;
    }
}
