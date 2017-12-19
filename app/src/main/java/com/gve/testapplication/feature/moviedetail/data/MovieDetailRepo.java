package com.gve.testapplication.feature.moviedetail.data;

import android.arch.persistence.room.EmptyResultSetException;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;

import com.gve.testapplication.core.data.ReactiveStoreSingular;
import com.gve.testapplication.feature.Movie;
import com.gve.testapplication.feature.MovieDetail;
import com.gve.testapplication.feature.MoviesPage;
import com.gve.testapplication.feature.data.MapperMovie;
import com.gve.testapplication.feature.data.MovieApiService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

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
    private ReactiveStoreSingular<MovieDetail> reactiveStore;
    private static final long TIME_AMOUNT_VALIDATE = 1 * 60 * 60 * 1000;

    private String apiKey;

    @Inject
    public MovieDetailRepo(@NonNull MovieApiService fetcher,
                           @NonNull ReactiveStoreSingular<MovieDetail> reactiveStore,
                           @NonNull @Named(API_KEY) String apiKey) {
        this.fetcher = fetcher;
        this.apiKey = apiKey;
        this.reactiveStore = reactiveStore;
    }

    public static String getKeyFromNumPage(long page) {
        return "MovieDetail movie: " + page;
    }

    public static BiFunction<MovieDetail, Long, String> getKeyFunction() {
        return (repos, id) -> getKeyFromNumPage(id);
    }

    private Single<MovieDetail> fetch(long id) {
        return fetcher.getMovieDetails(id, apiKey)
                .map(MapperMovie.mapperMovieDetailRawToMovieDetail())
                .doOnSuccess(movieDetail -> reactiveStore.storeSingular(movieDetail, id));
    }

    public Single<MovieDetail> get(long key) {
        Single<MovieDetail> storeSingle =
                reactiveStore.<Long, List<Movie>>getSingularSingle(getKeyFromNumPage(key))
                        .filter(stateList -> !isDataDeprecated(stateList.first))
                        .toSingle()
                        .onErrorResumeNext(error ->
                        {
                            if (error instanceof EmptyResultSetException || error instanceof NoSuchElementException) {
                                return Single.just(new Pair<Long, MovieDetail>(0L, null));
                            }
                            return Single.error(error);
                        })
                        .map(p -> p.second);

        return Single.concat(storeSingle, fetch(key))
                .filter(item -> item != null)
                .firstOrError()
                .subscribeOn(Schedulers.io());
    }

    public static boolean isDataDeprecated(long time) {
        return ((new Date()).getTime() - time) > TIME_AMOUNT_VALIDATE;
    }

}
