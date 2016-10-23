package io.github.hkusu.rxflux.ui.main;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.github.hkusu.rxflux.lib.flux.Dispatcher;
import io.github.hkusu.rxflux.lib.flux.Store;

@Singleton
public class MainStore extends Store {
    private Integer count = 0;
    private String message = "";
    private boolean initialized = false;

    @Inject
    MainStore(Dispatcher dispatcher) {
        super(dispatcher);

        observe(MainAction.COUNT_UP, action -> {
            count += (Integer) action.value;
        });

        observe(MainAction.COUNT_DOWN, action -> {
            count -= (Integer) action.value;
        });

        observe(MainAction.STORE_MESSAGE, action -> {
            message = (String) action.value;
        });

        observe(MainAction.STORE_INITIALIZED, action -> {
            initialized = (boolean) action.value;
        });

        observe(MainAction.POST_STORE_CHANGE_ONLY, action -> {
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
