package io.github.hkusu.rxflux.ui;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.github.hkusu.rxflux.lib.flux.Dispatcher;
import io.github.hkusu.rxflux.lib.flux.Store;

// NOTE: アプリケーション全体で共有する状態はここに書く
@Singleton
public class AppStore extends Store {
    @Inject
    public AppStore(Dispatcher dispatcher) {
        super(dispatcher);
    }
}
