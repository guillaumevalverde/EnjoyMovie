package com.gve.testapplication.feature.movieslist.data;

import com.gve.testapplication.feature.movieslist.presentation.MoviesPage;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by gve on 14/12/2017.
 */

public interface MovieApiService {

    @GET("/3/movie/top_rated")
    Single<MoviesPage> getWithPaging(@Query("api_key") String key,
                                     @Query("page") int page);
}
