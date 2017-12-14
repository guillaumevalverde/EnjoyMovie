package com.gve.testapplication.feature.data;

import com.gve.testapplication.feature.presentation.MovieRaw;
import com.gve.testapplication.feature.presentation.MoviesPage;

import java.util.List;

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
