package io.github.hkusu.rxflux.ui.main;

import javax.inject.Inject;

import io.github.hkusu.rxflux.lib.flux.Action;
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

    void initView() {
        someRepository.getMessage()
                .doOnNext(aString -> dispatch(MainAction.STORE_MESSAGE, aString, Action.NotifyStoreChanged.FALSE))
                .doOnNext(aString -> dispatch(MainAction.STORE_INITIALIZED, true, Action.NotifyStoreChanged.FALSE))
                .subscribeOn(Schedulers.io())
                .subscribe(aString -> {
                    dispatch(MainAction.NOTIFY_STORE_CHANGED, null);
                });
    }
}
