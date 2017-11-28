package com.gve.testapplication.ListOfRepoFeature.data;

import android.support.annotation.NonNull;

import com.gve.testapplication.core.data.ReactiveStoreSingular;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by gve on 29/11/2017.
 */

public class ListRepositoryRepo {
    private static final String REPO_XING = "xing";
    private static final int NUM_PER_PAGE = 10;
    private GitHubApiService fetcher;
    private ReactiveStoreSingular<List<Repository>> reactiveStore;


    @Inject
    public ListRepositoryRepo(@NonNull GitHubApiService fetcher,
                              @NonNull ReactiveStoreSingular<List<Repository>> reactiveStore) {
        this.fetcher = fetcher;
        this.reactiveStore = reactiveStore;
    }

    public static String getKeyFromNumPage(long page) {
        return "Repository xing: " + page;
    }

    public static BiFunction<List<Repository>, Long, String> getKeyFunction() {
        return (repos, id) -> getKeyFromNumPage(id);
    }

    private Single<List<Repository>> fetch(long id) {
        return fetcher.getRepo(REPO_XING, (int) id, NUM_PER_PAGE)
                .flatMap(MapperRepoRawToRepo.INSTANCE.getMapperListRepoRawToRepo())
                .doOnSuccess(list -> reactiveStore.storeSingular(list, id));
    }

    public Single<List<Repository>> get(long key) {
        Single<List<Repository>> storeSingle =
                reactiveStore.<Long, List<Repository>>getSingularSingle(getKeyFromNumPage(key))
                        .map(p -> p.second);

        return Single.concat(storeSingle, fetch(key))
                .filter(list -> !list.isEmpty())
                .first(new ArrayList<>())
                .subscribeOn(Schedulers.io());
    }

}
