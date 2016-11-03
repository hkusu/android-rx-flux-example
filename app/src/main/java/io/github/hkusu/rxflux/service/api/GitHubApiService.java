package io.github.hkusu.rxflux.service.api;

import java.util.List;

import io.github.hkusu.rxflux.model.entity.Repo;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface GitHubApiService {
    @GET("users/{user}/repos")
    Observable<List<Repo>> listRepos(@Path("user") String user);
}
