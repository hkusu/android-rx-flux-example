package io.github.hkusu.rxflux.di;

import javax.inject.Singleton;

import dagger.Component;
import io.github.hkusu.rxflux.lib.rx.SubscriptionManager;
import io.github.hkusu.rxflux.ui.main.MainActionCreator;
import io.github.hkusu.rxflux.ui.main.MainStore;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    MainStore mainStore();
    MainActionCreator mainAction();
    SubscriptionManager subscriptionManager();
}
