package io.github.hkusu.rxflux.ui.main;

import io.github.hkusu.rxflux.lib.flux.Action;

enum MainAction implements Action.Key {
    COUNT_UP,
    COUNT_DOWN,
    STORE_MESSAGE,
    STORE_INITIALIZED,
}
