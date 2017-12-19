package com.gve.testapplication.feature.data;

import com.gve.testapplication.feature.MovieDetail;
import com.gve.testapplication.feature.MovieDetailRaw;
import com.gve.testapplication.feature.MoviesPage;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by gve on 14/12/2017.
 */

public interface MovieApiService {

    @GET("/3/movie/top_rated")
    Single<MoviesPage> getWithPaging(@Query("api_key") String key,
                                     @Query("page") int page);

    @GET("/3/movie/{id}")
    Single<MovieDetailRaw> getMovieDetails(@Path("id") Long id,
                                           @Query("api_key") String key);

    @GET("/3/movie/{id}/similar")
    Single<MoviesPage> getSimilarMovie(@Path("id") Long id,
                                       @Query("api_key") String key,
                                       @Query("page") int page);

}
