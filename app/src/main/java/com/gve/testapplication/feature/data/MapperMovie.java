package com.gve.testapplication.feature.data;

import com.gve.testapplication.feature.Movie;
import com.gve.testapplication.feature.MovieRaw;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Function;

/**
 * Created by gve on 18/12/2017.
 */

public class MapperMovie {

    private static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w150/";

    private static Function<MovieRaw, Movie> mapperMovieRawToMovie() {
        return movieRaw ->
                new Movie(movieRaw.getId(),
                        movieRaw.getOriginal_title(),
                        movieRaw.getVote_average(),
                        IMAGE_BASE_URL + movieRaw.getPoster_path());
    }

    public static  Function<List<MovieRaw>, Single<List<Movie>>> mapperListMovieRawToMovie() {
        return repoRaw -> Observable.fromIterable(repoRaw)
                .map(mapperMovieRawToMovie()).toList();
    }
}
