package io.github.hkusu.rxflux.ui.main;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.github.hkusu.rxflux.lib.flux.Dispatcher;
import io.github.hkusu.rxflux.lib.flux.Store;
import io.github.hkusu.rxflux.ui.AppStore;

@Singleton
public class MainStore extends Store {
    private Integer count = 0;
    private String message = "";
    private boolean initialized = false;

    @Inject
    MainStore(Dispatcher dispatcher, AppStore appStore) {
        super(dispatcher);

        on(MainAction.COUNT_UP, action -> {
            count += (Integer) action.value;
        });

        on(MainAction.COUNT_DOWN, action -> {
            count -= (Integer) action.value;
        });

        on(MainAction.STORE_MESSAGE, action -> {
            message = (String) action.value;
        });

        on(MainAction.STORE_INITIALIZED, action -> {
            initialized = (boolean) action.value;
        });

        appStore.observeOnBackgroundThread(action -> {
            // do something ...
            notifyStoreChanged(action);
        });
    }

    boolean isInitialized() {
        return initialized;
    }

    Integer getCount() {
        return count;
    }

    String getMessage() {
        return message;
    }
}
