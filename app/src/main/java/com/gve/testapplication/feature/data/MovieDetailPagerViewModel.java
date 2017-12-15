package com.gve.testapplication.feature.data;

import android.support.annotation.NonNull;
import android.util.Log;

import com.gve.testapplication.feature.Movie;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;

import static com.gve.testapplication.core.injection.module.BootCampModule.API_KEY;


/**
 * Created by gve on 29/11/2017.
 */

public class MovieDetailPagerViewModel {
    CompositeDisposable disposable = new CompositeDisposable();
    private MovieApiService fetcher;
    private String apiKey;
    private List<Movie> movies = new ArrayList<>();
    private Movie movieRef;
    private int numPage = 1;
    private int maxPage;
    private int numPageInMemmory = 0;
    private int index = 0;
    private BehaviorSubject<List<Movie>> movieSubject = BehaviorSubject.create();


    public MovieDetailPagerViewModel(@NonNull Movie movieRef,
                                     @NonNull MovieApiService fetcher,
                                     @NonNull @Named(API_KEY) String apiKey) {
        this.fetcher = fetcher;
        this.apiKey = apiKey;
        this.movieRef = movieRef;
        movies.add(movieRef);
        disposable.add(fetcher.getSimilarMovie(movieRef.getId(), apiKey, 1)
                .flatMap(moviesInPage -> MapperMovieRawToMovie.INSTANCE
                        .getMapperListMovieRawToMovie().apply(moviesInPage.getResults()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movies -> {
                     Log.v("gui", "getOtherMovies: " + movies.size());
                    movieSubject.onNext(movies);
                }, error -> Log.v("gui", "error " + error.getMessage())));


    }

    public Flowable<List<Movie>> getMovie() {
        return movieSubject.toFlowable(BackpressureStrategy.LATEST);
    }

    public Movie getMovie(int position) {
        Log.v("gui", "position in view model: " + position + " movies size: " + movies.size());
        if (position < movies.size())
            return movies.get(position);
        else return null;
    }

    public static class FactoryPagerViewModel {

        private MovieApiService fetcher;
        private String apiKey;

        @Inject
        public FactoryPagerViewModel(@NonNull MovieApiService fetcher,
                                     @NonNull @Named(API_KEY) String apiKey) {
            this.apiKey = apiKey;
            this.fetcher = fetcher;
        }

        public MovieDetailPagerViewModel getViewModel(Movie movieRef) {
            return new MovieDetailPagerViewModel(movieRef, fetcher, apiKey);
        }
    }

    public int getCount() {
        return movies.size();
    }


}
