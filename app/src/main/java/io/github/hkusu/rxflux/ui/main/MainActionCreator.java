package io.github.hkusu.rxflux.ui.main;

import android.util.Log;

import javax.inject.Inject;

import io.github.hkusu.rxflux.lib.flux.ActionCreator;
import io.github.hkusu.rxflux.lib.flux.Dispatcher;
import io.github.hkusu.rxflux.model.entity.Repo;
import io.github.hkusu.rxflux.model.repository.GitHubRepoRepository;
import io.github.hkusu.rxflux.model.repository.SomeRepository;
import rx.schedulers.Schedulers;

public class MainActionCreator extends ActionCreator {
    private final SomeRepository someRepository;
    private final GitHubRepoRepository gitHubRepoRepository;

    @Inject
    MainActionCreator(Dispatcher dispatcher, SomeRepository someRepository, GitHubRepoRepository gitHubRepoRepository) {
        super(dispatcher);
        this.someRepository = someRepository;
        this.gitHubRepoRepository = gitHubRepoRepository;
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
                .doOnNext(aString -> {
                    gitHubRepoRepository.list("hkusu")
                            .subscribeOn(Schedulers.io())
                            .subscribe(aRepoList -> {
                                for (Repo repo: aRepoList) {
                                    Log.d("RETROFIT_TEST", repo.name);
                                }
                            });
                })
                .subscribeOn(Schedulers.io())
                .subscribe(aString -> {
                    dispatch(MainAction.STORE_INITIALIZED, true);
                });
    }
}
