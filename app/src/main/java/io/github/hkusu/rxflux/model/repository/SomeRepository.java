package io.github.hkusu.rxflux.model.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.github.hkusu.rxflux.service.SomeService;
import rx.Observable;
import rx.schedulers.Schedulers;

@Singleton
public class SomeRepository {
    private final SomeService someService;

    @Inject
    SomeRepository(SomeService someService) {
        this.someService = someService;
    }

    public Observable<String> getMessage() {
        return someService.getMessage()
                .subscribeOn(Schedulers.io());
    }
}
