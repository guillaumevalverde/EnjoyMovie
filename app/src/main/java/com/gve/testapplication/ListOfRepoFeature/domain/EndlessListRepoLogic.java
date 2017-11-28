package com.gve.testapplication.ListOfRepoFeature.domain;

import android.support.annotation.NonNull;

import com.gve.testapplication.ListOfRepoFeature.data.ListRepositoryRepo;
import com.gve.testapplication.ListOfRepoFeature.presentation.RepoDisplayableMapper;
import com.gve.testapplication.core.recyclerview.DisplayableItem;
import com.gve.testapplication.core.recyclerview.RecyclerViewConstant;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subjects.BehaviorSubject;

import static com.gve.testapplication.core.recyclerview.DisplayableItem.toDisplayableItem;

/**
 * Created by gve on 28/11/2017.
 */

public class EndlessListRepoLogic {
    private CompositeDisposable disposable = new CompositeDisposable();
    private RepoDisplayableMapper mapper;
    private ListRepositoryRepo repo;
    private BehaviorSubject<List<DisplayableItem>> listBehaviorSubject =
            BehaviorSubject.createDefault(new ArrayList<DisplayableItem>());

    private int numPage = 1;

    @Inject
    public EndlessListRepoLogic(@NonNull RepoDisplayableMapper mapper,
                                @NonNull ListRepositoryRepo repo) {

        this.mapper = mapper;
        this.repo = repo;
        fetch();
    }

    private void fetch() {
        if (listBehaviorSubject.getValue().size() > 0) {
            listBehaviorSubject.onNext(addProgressEmptyRow(listBehaviorSubject.getValue()));
        }
        disposable.add(repo.get(numPage)
                .map(mapper)
                .map(list -> {
                    List<DisplayableItem> current =
                            removeLastItemIfEmptyType(listBehaviorSubject.getValue());
                    current.addAll(list);
                    return current;
                })
                .subscribe(list -> {
                    listBehaviorSubject.onNext(list);
                    numPage++;
                    }, error -> listBehaviorSubject.onNext(
                        removeLastItemIfEmptyType(listBehaviorSubject.getValue()))));
    }

    public Callable callableFetch() {
        return () -> {
            fetch();
            return "fetched";
        };
    }

    private List<DisplayableItem> addProgressEmptyRow(List<DisplayableItem> list) {
        List<DisplayableItem> listReturn = new ArrayList<>();
        listReturn.addAll(list);
        listReturn.add(toDisplayableItem(new Object(), RecyclerViewConstant.EMPTY_TYPE));
        return listReturn;
    }

    private List<DisplayableItem> removeLastItemIfEmptyType(List<DisplayableItem> list) {
        List<DisplayableItem> listReturn = new ArrayList<>();
        listReturn.addAll(list);

        if (listReturn.size() > 0
                && listReturn.get(listReturn.size() - 1).type()
                == RecyclerViewConstant.EMPTY_TYPE) {
            listReturn.remove(listReturn.size() - 1);
        }
        return listReturn;
    }

    public Flowable<List<DisplayableItem>> getDisplayableList() {
        return listBehaviorSubject.toFlowable(BackpressureStrategy.BUFFER);
    }
}
