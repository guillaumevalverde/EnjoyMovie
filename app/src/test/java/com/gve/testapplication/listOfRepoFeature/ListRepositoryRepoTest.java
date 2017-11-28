package com.gve.testapplication.listOfRepoFeature;

import android.support.v4.util.Pair;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gve.testapplication.ListOfRepoFeature.data.GitHubApiService;
import com.gve.testapplication.ListOfRepoFeature.data.ListRepositoryRepo;
import com.gve.testapplication.ListOfRepoFeature.data.Repository;
import com.gve.testapplication.core.data.ReactiveStoreSingular;
import com.gve.testapplication.listOfRepoFeature.data.PojoUtils;
import com.gve.testapplication.test_common.BaseTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by gve on 29/11/2017.
 */

public class ListRepositoryRepoTest extends BaseTest {

    @Mock
    GitHubApiService gitHubApiService;

    @Mock
    ReactiveStoreSingular<List<Repository>> store;

    private Gson gson;

    @Before
    public void setUp() {
        final GsonBuilder builder = new GsonBuilder();
        gson = builder.create();
    }

    public static Single<Pair<Long, List<Repository>>> NonEmptySingle(Gson gson) {
        return Single.just(new Pair(0, PojoUtils.getList(gson).blockingGet()));
    }

    public static Single<Pair<Long, List<Repository>>> emptySingle =
            Single.just(new Pair(0, new ArrayList<Repository>()));


    @Test
    public void WithEmptyStoreFetchTest() {
        ListRepositoryRepo listRepositoryRepo = new ListRepositoryRepo(gitHubApiService, store);
        when(gitHubApiService.getRepo(Mockito.anyString(), Mockito.anyInt(),Mockito.anyInt()))
                .thenReturn(PojoUtils.getListRaw(gson));

        when(store.getSingularSingle(Mockito.anyString()))
                .thenReturn(emptySingle);

        TestObserver<List<Repository>> testObserver = listRepositoryRepo.get(0).test();
        testObserver.assertComplete();
        testObserver.assertNoErrors();
        testObserver.assertValueAt(0, repositories -> repositories.size() == 2);
    }

    @Test
    public void withStoreNonEmptyNoFetchTest() {
        ListRepositoryRepo listRepositoryRepo = new ListRepositoryRepo(gitHubApiService, store);
        when(gitHubApiService.getRepo(Mockito.anyString(), Mockito.anyInt(),Mockito.anyInt()))
                .thenReturn(Single.just(new ArrayList<>()));

        when(store.getSingularSingle(Mockito.anyString()))
                .thenReturn(NonEmptySingle(gson));

        TestObserver<List<Repository>> testObserver = listRepositoryRepo.get(0).test();
        testObserver.assertComplete();
        testObserver.assertNoErrors();
        testObserver.assertValueAt(0, repositories -> repositories.size() == 2);

    }

}
