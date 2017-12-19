package com.gve.testapplication;

import android.support.v4.util.Pair;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gve.testapplication.core.presentation.recyclerview.DisplayableItem;
import com.gve.testapplication.core.presentation.recyclerview.endlesslistscroll.EndlessListDomainLogic;
import com.gve.testapplication.feature.data.Movie;
import com.gve.testapplication.feature.movieslist.data.ListMovieRepo;
import com.gve.testapplication.feature.data.MovieUtil;
import com.gve.testapplication.feature.movieslist.presentation.recyclerview.MovieDisplayableMapper;
import com.gve.testapplication.test_common.BaseTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.subscribers.TestSubscriber;
import polanski.option.Option;

import static junit.framework.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;

/**
 * Created by gve on 18/12/2017.
 */

public class EndlessListRepoLogicTest extends BaseTest {

    @Mock
    private ListMovieRepo repo;

    private Gson gson;

    @Before
    public void setUp() {
        final GsonBuilder builder = new GsonBuilder();
        gson = builder.create();
    }


    @Test
    public void logicWithNoScrollTriggeringFetchTest() throws Exception {
        Mockito.when(repo.get(anyLong())).thenReturn(MovieUtil.getMoviesSingle(gson));
        EndlessListDomainLogic<Movie> viewModel = new EndlessListDomainLogic(new MovieDisplayableMapper(), repo);

        TestSubscriber<Pair<Option<Throwable>, List<DisplayableItem>>> testSubscriber =
                viewModel.getDisplayableList().test();

        assertEquals(1, testSubscriber.values().size());
        assertEquals(20, testSubscriber.values().get(0).second.size());
        testSubscriber.assertNoErrors();
        testSubscriber.assertNotComplete();
    }

    /**
     * scrolling behavior trigger the call of viewModel.getCallableFetch().call()
     * @throws Exception
     */
    @Test
    public void logicWithOneScrollTriggeringFetchTest() throws Exception {
        Mockito.when(repo.get(anyLong())).thenReturn(MovieUtil.getMoviesSingle(gson));
        EndlessListDomainLogic<Movie> viewModel = new EndlessListDomainLogic(new MovieDisplayableMapper(), repo);

        List<List<DisplayableItem>> listResult = new ArrayList<>();
        TestSubscriber<Pair<Option<Throwable>, List<DisplayableItem>>> testSubscriber
                = new TestSubscriber<>();

        viewModel.getDisplayableList()
                .doOnNext(list -> {
                    System.out.println("list size on next: " + list.second.size());
                    listResult.add(list.second);
                })
                .subscribe(testSubscriber);

        viewModel.fetch();


        testSubscriber.assertNoErrors();
        testSubscriber.assertNotComplete();
        assertEquals(3, testSubscriber.values().size());
        assertEquals(3, listResult.size());

        // first load of a list of 2 items
        assertEquals(20, listResult.get(0).size());

        // enable fetch, add progress bar to the current list
        assertEquals(21, listResult.get(1).size());

        // new list fetched and added to current list removing the progress bar item
        assertEquals(40, listResult.get(2).size());
    }

    @Test
    public void withEmptyStoreAndErrorNetworkTest() throws Exception {
        Mockito.when(repo.get(anyLong())).thenReturn(MovieUtil.getMoviesSingle(gson),
                Single.error(new Exception()));

        EndlessListDomainLogic<Movie> viewModel = new EndlessListDomainLogic(new MovieDisplayableMapper(), repo);
        List<List<DisplayableItem>> listResult = new ArrayList<>();

        TestSubscriber<Pair<Option<Throwable>, List<DisplayableItem>>> testSubscriber = new TestSubscriber<>();
        viewModel.getDisplayableList()
                .doOnNext(list -> {
                    System.out.println("list size on next: " + list.second.size());
                    listResult.add(list.second);
                })
                .subscribe(testSubscriber);

        viewModel.fetch();


        testSubscriber.assertNoErrors();
        testSubscriber.assertNotComplete();
        assertEquals(3, testSubscriber.values().size());
        assertEquals(3, listResult.size());

        // first load of 2 items
        assertEquals(20, listResult.get(0).size());

        // enable fetch, add progress bar to the current list
        assertEquals(21, listResult.get(1).size());

        // error, we remove progress item, back to size of 2
        assertEquals(20, listResult.get(2).size());
    }
}
