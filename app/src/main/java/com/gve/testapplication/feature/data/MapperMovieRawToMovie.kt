package com.gve.testapplication.feature.data


import com.gve.testapplication.feature.Movie
import com.gve.testapplication.feature.MovieRaw
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.Function

/**
 * Created by gve on 28/11/2017.
 */

object MapperMovieRawToMovie {

    private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w150/"

    private val mapperMovieRawToMovie: io.reactivex.functions.Function<MovieRaw, Movie> =
            Function { movieRaw ->
                Movie(movieRaw.id,
                        movieRaw.original_title,
                        movieRaw.vote_average,
                        IMAGE_BASE_URL + movieRaw.poster_path)
            }

    val mapperListMovieRawToMovie: io.reactivex.functions.Function<List<MovieRaw>, Single<List<Movie>>> =
            Function { repoRaw -> Observable.fromIterable<MovieRaw>(repoRaw)
                    .map(mapperMovieRawToMovie)
                    .toList()}
}
