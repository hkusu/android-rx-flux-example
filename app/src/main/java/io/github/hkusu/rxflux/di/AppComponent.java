package io.github.hkusu.rxflux.di;

import javax.inject.Singleton;

import dagger.Component;
import io.github.hkusu.rxflux.ui.AppActionCreator;
import io.github.hkusu.rxflux.ui.AppStore;
import io.github.hkusu.rxflux.ui.main.MainActionCreator;
import io.github.hkusu.rxflux.ui.main.MainStore;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    AppStore appStore();
    AppActionCreator appActionCreator();
    MainStore mainStore();
    MainActionCreator mainActionCreator();
}
