package io.github.hkusu.rxflux.ui;

import javax.inject.Inject;

import io.github.hkusu.rxflux.lib.flux.ActionCreator;
import io.github.hkusu.rxflux.lib.flux.Dispatcher;

// NOTE: アプリケーション全体で共有するアクションはここに書く
public class AppActionCreator extends ActionCreator {
    @Inject
    public AppActionCreator(Dispatcher dispatcher) {
        super(dispatcher);
    }
}
