package com.gve.testapplication.feature.data;

import android.support.annotation.NonNull;

import com.gve.testapplication.core.data.ReactiveStoreSingular;
import com.gve.testapplication.feature.Movie;
import com.gve.testapplication.feature.MovieDetail;
import com.gve.testapplication.feature.MoviesPage;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

import static com.gve.testapplication.core.injection.module.BootCampModule.API_KEY;


/**
 * Created by gve on 29/11/2017.
 */

public class MovieDetailRepo {
    private MovieApiService fetcher;

    private String apiKey;

    @Inject
    public MovieDetailRepo(@NonNull MovieApiService fetcher,
                           @NonNull @Named(API_KEY) String apiKey) {
        this.fetcher = fetcher;
        this.apiKey = apiKey;
    }

    public Single<MovieDetail> fetch(long id) {
        return fetcher.getMovieDetails(id, apiKey);
    }

}
