package com.gve.testapplication.feature.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gve.testapplication.feature.Movie;
import com.gve.testapplication.feature.MovieDetail;
import com.gve.testapplication.feature.MovieRaw;
import com.gve.testapplication.feature.MoviesPage;
import com.gve.testapplication.test_common.BaseTest;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import io.reactivex.Single;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by gve on 14/12/2017.
 */

public class pojoMovieTest extends BaseTest {

    private Gson gson;

    @Before
    public void setUp() {
        final GsonBuilder builder = new GsonBuilder();
        gson = builder.create();
    }

    @Test
    public void parseMovieJsonRaw() {
        MovieRaw movie = MovieUtil.getMovieRaw(gson);

        assertTrue(movie != null);
        assertFalse(movie.getAdult());
        assertEquals(244786, movie.getId());
        assertEquals(8.29, movie.getVote_average(), 0.001);
        assertEquals(2059, movie.getVote_count());
    }

    @Test
    public void parseMovieDetailJsonRaw() {
        MovieDetail movie = MovieUtil.getMovieDetail(gson);

        assertTrue(movie != null);
        assertFalse(movie.getAdult());
        assertEquals(550, movie.getId());
    }

    @Test
    public void parseMoviesInPageJsonRaw() {
        MoviesPage movies = MovieUtil.getMoviesInPage(gson);

        assertTrue(movies != null);
        assertEquals(5206, movies.getTotal_results());
        assertEquals(261, movies.getTotal_pages());
        assertEquals(20, movies.getResults().size());
    }

    @Test public void mapperMovieRawToVideoTest() {
        MoviesPage movies = MovieUtil.getMoviesInPage(gson);
        List<Movie> movieList = Single.just(movies)
                .map(MoviesPage::getResults)
                .flatMap(list -> MapperMovie.mapperListMovieRawToMovie().apply(list))
                .blockingGet();

        assertEquals(movies.getResults().size(), movieList.size());
    }
}
