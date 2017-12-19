package com.gve.testapplication.feature.data;

import com.gve.testapplication.feature.Utils;

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

    public static Function<MovieDetailRaw, MovieDetail> mapperMovieDetailRawToMovieDetail() {
        return movieRaw ->
                new MovieDetail(movieRaw.getId(),
                        Utils.getNumberInCurrency(movieRaw.getBudget()),
                        movieRaw.getOriginal_language(),
                        movieRaw.getOriginal_title(),
                        movieRaw.getOverview(),
                        movieRaw.getPopularity(),
                        IMAGE_BASE_URL + movieRaw.getPoster_path(),
                        Utils.getDate(movieRaw.getRelease_date()),
                        Utils.getNumberInCurrency(movieRaw.getRevenue()),
                        movieRaw.getVote_count(),
                        movieRaw.getVote_average());
    }

    public static  Function<List<MovieRaw>, Single<List<Movie>>> mapperListMovieDetailRawToMovieDetail() {
        return repoRaw -> Observable.fromIterable(repoRaw)
                .map(mapperMovieRawToMovie()).toList();
    }
}
