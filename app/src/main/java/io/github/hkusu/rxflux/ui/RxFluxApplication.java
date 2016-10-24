package io.github.hkusu.rxflux.ui;

import android.app.Application;

import io.github.hkusu.rxflux.di.AppComponent;
import io.github.hkusu.rxflux.di.AppModule;
import io.github.hkusu.rxflux.di.DaggerAppComponent;

public class RxFluxApplication extends Application {
    private AppComponent appComponent;

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }
}
