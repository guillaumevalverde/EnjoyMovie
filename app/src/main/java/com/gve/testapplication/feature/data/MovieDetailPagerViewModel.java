package com.gve.testapplication.feature.data;

import android.support.annotation.NonNull;

import com.gve.testapplication.feature.Movie;
import com.gve.testapplication.feature.MoviesPage;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;

import static com.gve.testapplication.core.injection.module.BootCampModule.API_KEY;


/**
 * Created by gve on 29/11/2017.
 */

public class MovieDetailPagerViewModel {
    private MovieApiService fetcher;
    private String apiKey;
    private Movie movieRef;


    public MovieDetailPagerViewModel(@NonNull Movie movieRef,
                                     @NonNull MovieApiService fetcher,
                                     @NonNull @Named(API_KEY) String apiKey) {
        this.fetcher = fetcher;
        this.apiKey = apiKey;
        this.movieRef = movieRef;
    }

    public Single<List<Movie>> getMovieWithSimilar() {
        return fetcher.getSimilarMovie(movieRef.getId(), apiKey, 1)
                .map(MoviesPage::getResults)
                .flatMap(MapperMovieRawToMovie.INSTANCE.getMapperListMovieRawToMovie())
                .map(moviesSimilar -> {
                    List<Movie> movies = new ArrayList<>();
                    movies.add(movieRef);
                    movies.addAll(moviesSimilar);
                    return movies;
                });
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
}
