package io.github.hkusu.rxflux.ui.main;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.github.hkusu.rxflux.lib.flux.Action;
import io.github.hkusu.rxflux.lib.flux.ActionCreator;
import io.github.hkusu.rxflux.lib.flux.Dispatcher;
import io.github.hkusu.rxflux.model.repository.SomeRepository;
import rx.schedulers.Schedulers;

@Singleton
public class MainActionCreator extends ActionCreator {
    private SomeRepository someRepository;

    @Inject
    MainActionCreator(Dispatcher dispatcher, SomeRepository someRepository) {
        super(dispatcher);
        this.someRepository = someRepository;
    }

    void countUp(int num) {
        post(MainAction.COUNT_UP, num);
    }

    void countDown(int num) {
        post(MainAction.COUNT_DOWN, num);
    }

    void initView() {
        someRepository.getMessage()
                .doOnNext(aString -> post(MainAction.STORE_MESSAGE, aString, Action.PostStoreChange.FALSE))
                .doOnNext(aString -> post(MainAction.STORE_INITIALIZED, true, Action.PostStoreChange.FALSE))
                .subscribeOn(Schedulers.io())
                .subscribe(aString -> {
                    post(MainAction.POST_STORE_CHANGE_ONLY, null);
                });
    }
}
