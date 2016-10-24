package io.github.hkusu.rxflux.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.github.hkusu.rxflux.lib.flux.Dispatcher;

@Singleton
@Module
public class AppModule {
    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application application() {
        return application;
    }

    @Provides
    @Singleton
    public Dispatcher dispatcher() {
        return new Dispatcher();
    }
}
