package com.gve.testapplication.feature.data;

import android.arch.persistence.room.EmptyResultSetException;
import android.support.v4.util.Pair;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gve.testapplication.core.data.ReactiveStoreSingular;
import com.gve.testapplication.feature.movieslist.data.ListMovieRepo;
import com.gve.testapplication.test_common.BaseTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by gve on 19/12/2017.
 */

public class ListMovieRepoTest extends BaseTest {

    @Mock
    MovieApiService fetcher;

    @Mock
    ReactiveStoreSingular<List<Movie>> store;

    private Gson gson;

    @Before
    public void setUp() {
        final GsonBuilder builder = new GsonBuilder();
        gson = builder.create();
    }

    public static Single<Pair<Long, List<Movie>>> getNonEmptySingle(Gson gson) throws Exception {
        return Single.just(new Pair(new Date().getTime(), MovieUtil.getMoviesSingle(gson).blockingGet()));
    }

    public static Single<Pair<Long, List<Movie>>> getNonEmptyDeprecatedSingle(Gson gson) throws Exception {
        return Single.just(new Pair(0L, MovieUtil.getMoviesSingle(gson).blockingGet()));
    }

    public static Single<Pair<Long, List<Movie>>> emptySingle =
            Single.just(new Pair(0, new ArrayList<Movie>()));

    @Test
    public void WithEmptyStoreFetchTest() {
        ListMovieRepo repo = new ListMovieRepo(fetcher, store, "apiKey");
        when(fetcher.getWithPaging(Mockito.anyString(),Mockito.anyInt()))
                .thenReturn(Observable.just(MovieUtil.getMoviesInPage(gson)).delay(1000, TimeUnit.MILLISECONDS).singleOrError());

        when(store.getSingularSingle(Mockito.anyString()))
                .thenReturn(Single.error(new EmptyResultSetException("empty")));

        TestObserver<List<Movie>> testObserver = repo.get(0).test();
        testObserver.assertComplete();
        testObserver.assertNoErrors();
        testObserver.assertValueAt(0, repositories -> repositories.size() == 20);
        verify(fetcher,  times(1)).getWithPaging(Mockito.anyString(),Mockito.anyInt());
    }


    @Test
    public void storeWithDataDeprecatedFetchTest() throws Exception {
        ListMovieRepo repo = new ListMovieRepo(fetcher, store, "apiKey");
        when(fetcher.getWithPaging(Mockito.anyString(),Mockito.anyInt()))
                .thenReturn(Observable.just(MovieUtil.getMoviesInPage(gson)).delay(1000, TimeUnit.MILLISECONDS).singleOrError());

        when(store.getSingularSingle(Mockito.anyString()))
                .thenReturn(getNonEmptyDeprecatedSingle(gson));

        TestObserver<List<Movie>> testObserver = repo.get(0L).test();
        testObserver.assertComplete();
        testObserver.assertNoErrors();
        testObserver.assertValueAt(0, repositories -> repositories.size() == 20);
        verify(fetcher,  times(1)).getWithPaging(Mockito.anyString(),Mockito.anyInt());
    }

    @Test
    public void withStoreNonEmptyNoFetchTest() throws Exception {
        ListMovieRepo repo = new ListMovieRepo(fetcher, store, "apiKey");

        when(fetcher.getWithPaging(Mockito.anyString(),Mockito.anyInt()))
                .thenReturn(Single.just(MovieUtil.getMoviesInPage(gson)));

        when(store.getSingularSingle(Mockito.anyString()))
                .thenReturn(getNonEmptySingle(gson));

        TestObserver<List<Movie>> testObserver = repo.get(0).test();
        testObserver.assertComplete();
        testObserver.assertNoErrors();
        testObserver.assertValueAt(0, repositories -> repositories.size() == 20);
    }

    @Test
    public void withStoreEmptyErrorNetworkTest() throws Exception {
        ListMovieRepo repo = new ListMovieRepo(fetcher, store, "apiKey");

        when(fetcher.getWithPaging(Mockito.anyString(),Mockito.anyInt()))
                .thenReturn(Single.error(new Exception("network")));

        when(store.getSingularSingle(Mockito.anyString()))
                .thenReturn(Single.error(new EmptyResultSetException("empty")));

        TestObserver<List<Movie>> testObserver = repo.get(0).test();
        testObserver.assertError(throwable -> throwable.getMessage().contentEquals("network"));

    }

    @Test public void isDataDeprecated() {
        assertTrue(ListMovieRepo.isDataDeprecated(0L));
    }

}
