package io.github.hkusu.rxflux.model.repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.github.hkusu.rxflux.model.entity.Repo;
import io.github.hkusu.rxflux.service.api.GitHubApiService;
import rx.Observable;

@Singleton
public class GitHubRepoRepository {
    private final GitHubApiService gitHubApiService;

    @Inject
    public GitHubRepoRepository(GitHubApiService gitHubApiService) {
        this.gitHubApiService = gitHubApiService;
    }

    public Observable<List<Repo>> list(String user) {
        return gitHubApiService.listRepos(user);
    }
}
