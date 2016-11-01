package io.github.hkusu.rxflux.ui.main;

import javax.inject.Inject;

import io.github.hkusu.rxflux.lib.flux.ActionCreator;
import io.github.hkusu.rxflux.lib.flux.Dispatcher;
import io.github.hkusu.rxflux.model.repository.SomeRepository;
import rx.schedulers.Schedulers;

public class MainActionCreator extends ActionCreator {
    private SomeRepository someRepository;

    @Inject
    MainActionCreator(Dispatcher dispatcher, SomeRepository someRepository) {
        super(dispatcher);
        this.someRepository = someRepository;
    }

    void countUp(int num) {
        dispatch(MainAction.COUNT_UP, num);
    }

    void countDown(int num) {
        dispatch(MainAction.COUNT_DOWN, num);
    }

    void initialize() {
        someRepository.getMessage()
                .doOnNext(aString -> dispatchSkipNotify(MainAction.STORE_MESSAGE, aString))
                .subscribeOn(Schedulers.io())
                .subscribe(aString -> {
                    dispatch(MainAction.STORE_INITIALIZED, true);
                });
    }
}
