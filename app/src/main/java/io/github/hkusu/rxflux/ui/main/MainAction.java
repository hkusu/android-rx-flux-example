package io.github.hkusu.rxflux.ui.main;

interface MainAction {
    String COUNT_UP = "count_up";
    String COUNT_DOWN = "count_down";
    String STORE_MESSAGE = "store_message";
    String STORE_INITIALIZED = "store_initialized";
    String NOTIFY_STORE_CHANGED = "post_store_change_only";
}
