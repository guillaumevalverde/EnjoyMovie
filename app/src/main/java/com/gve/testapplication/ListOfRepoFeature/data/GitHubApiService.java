package com.gve.testapplication.ListOfRepoFeature.data;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by gve on 28/11/2017.
 */

public interface GitHubApiService {

    @GET("users/{user}/repos")
    Single<List<RepoRaw>> getRepo(@Path("user") String user,
                                  @Query("page") int page,
                                  @Query("per_page") int perPage);
}
