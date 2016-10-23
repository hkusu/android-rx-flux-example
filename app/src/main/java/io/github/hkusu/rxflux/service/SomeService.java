package io.github.hkusu.rxflux.service;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.schedulers.Schedulers;

@Singleton
public class SomeService {

    @Inject
    SomeService() {}

    public Observable<String> getMessage() {
        // NOTE: 実際は API や DB アクセスなど
        return Observable.just("この文言はサーバ等からデータを取得する例です。")
                .subscribeOn(Schedulers.io());
    }
}
