package com.gve.testapplication.feature.moviedetail.data;

import android.arch.persistence.room.EmptyResultSetException;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;

import com.gve.testapplication.core.data.ReactiveStoreSingular;
import com.gve.testapplication.core.injection.qualifiers.Similar;
import com.gve.testapplication.feature.data.Movie;
import com.gve.testapplication.feature.data.MoviesPage;
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

public class MovieDetailPagerRepo {
    private MovieApiService fetcher;
    private String apiKey;
    private Movie movieRef;
    private ReactiveStoreSingular<List<Movie>> reactiveStore;
    private static final long TIME_AMOUNT_VALIDATE = 1 * 60 * 60 * 1000;

    public MovieDetailPagerRepo(@NonNull Movie movieRef,
                                @NonNull ReactiveStoreSingular<List<Movie>> reactiveStore,
                                @NonNull MovieApiService fetcher,
                                @NonNull @Named(API_KEY) String apiKey) {
        this.fetcher = fetcher;
        this.reactiveStore = reactiveStore;
        this.apiKey = apiKey;
        this.movieRef = movieRef;
    }

    public static String getKeyFromNumPage(long id) {
        return "Movie similar: " + id;
    }

    public static BiFunction<List<Movie>, Long, String> getKeyFunction() {
        return (repos, id) -> getKeyFromNumPage(id);
    }

    public Single<List<Movie>> fetch() {
        return fetcher.getSimilarMovie(movieRef.getId(), apiKey, 1)
                .map(MoviesPage::getResults)
                .flatMap(MapperMovie.mapperListMovieRawToMovie())
                .map(moviesSimilar -> {
                    List<Movie> movies = new ArrayList<>();
                    movies.add(movieRef);
                    movies.addAll(moviesSimilar);
                    return movies;
                })
                .doOnSuccess(list -> reactiveStore.storeSingular(list, movieRef.getId()));
    }

    public Single<List<Movie>> get() {
        Single<List<Movie>> storeSingle =
                reactiveStore.<Long, List<Movie>>getSingularSingle(getKeyFromNumPage(movieRef.getId()))
                        .filter(stateList -> !isDataDeprecated(stateList.first))
                        .toSingle()
                        .onErrorResumeNext(error ->
                        {
                            if (error instanceof EmptyResultSetException || error instanceof NoSuchElementException) {
                                return Single.just(new Pair<Long, List<Movie>>(0L, new ArrayList<Movie>()));
                            }
                            return Single.error(error);
                        })
                        .map(p -> p.second);

        return Single.concat(storeSingle, fetch())
                .filter(list -> !list.isEmpty())
                .first(new ArrayList<>())
                .subscribeOn(Schedulers.io());
    }

    public static boolean isDataDeprecated(long time) {
        return ((new Date()).getTime() - time) > TIME_AMOUNT_VALIDATE;
    }

    public static class FactoryMovieDetailPagerRepo {

        private MovieApiService fetcher;
        private ReactiveStoreSingular<List<Movie>> reactiveStore;
        private String apiKey;

        @Inject
        public FactoryMovieDetailPagerRepo(@NonNull MovieApiService fetcher,
                                           @NonNull @Similar ReactiveStoreSingular<List<Movie>> reactiveStore,
                                           @NonNull @Named(API_KEY) String apiKey) {
            this.apiKey = apiKey;
            this.fetcher = fetcher;
            this.reactiveStore = reactiveStore;
        }

        public MovieDetailPagerRepo getViewModel(Movie movieRef) {
            return new MovieDetailPagerRepo(movieRef, reactiveStore, fetcher, apiKey);
        }
    }
}
