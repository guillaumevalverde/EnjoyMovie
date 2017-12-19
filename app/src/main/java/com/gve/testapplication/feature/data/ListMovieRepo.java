package com.gve.testapplication.feature.data;

import android.arch.persistence.room.EmptyResultSetException;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;
import android.util.Log;

import com.gve.testapplication.core.data.ReactiveStoreSingular;
import com.gve.testapplication.core.presentation.recyclerview.endlesslistscroll.RepoInfiniteScrolling;
import com.gve.testapplication.feature.Movie;
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

public class ListMovieRepo implements RepoInfiniteScrolling<Movie> {
    private static final String REPO_MOVIE = "movie";
    private MovieApiService fetcher;
    private ReactiveStoreSingular<List<Movie>> reactiveStore;

    private String apiKey;

    @Inject
    public ListMovieRepo(@NonNull MovieApiService fetcher,
                         @NonNull ReactiveStoreSingular<List<Movie>> reactiveStore,
                         @NonNull @Named(API_KEY) String apiKey) {
        this.fetcher = fetcher;
        this.reactiveStore = reactiveStore;
        this.apiKey = apiKey;
    }

    public static String getKeyFromNumPage(long page) {
        return "Movie movie: " + page;
    }

    public static BiFunction<List<Movie>, Long, String> getKeyFunction() {
        return (repos, id) -> getKeyFromNumPage(id);
    }

    private Single<List<Movie>> fetch(long id) {
        return fetcher.getWithPaging(apiKey, (int) id)
                .map(MoviesPage::getResults)
                .flatMap(MapperMovie.mapperListMovieRawToMovie())
                .doOnSuccess(list -> reactiveStore.storeSingular(list, id));
    }

    public Single<List<Movie>> get(long key) {
        Single<List<Movie>> storeSingle =
                reactiveStore.<Long, List<Movie>>getSingularSingle(getKeyFromNumPage(key))
                        .onErrorResumeNext(error ->
                        {
                            if (error instanceof EmptyResultSetException) {
                                return Single.just(new Pair<Long, List<Movie>>(0L, new ArrayList<Movie>()));
                            }
                            return Single.error(error);

                        })
                        .map(p -> p.second);

        return Single.concat(storeSingle, fetch(key))
                .filter(list -> !list.isEmpty())
                .first(new ArrayList<>())
                .subscribeOn(Schedulers.io());
    }

}
