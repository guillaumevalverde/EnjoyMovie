package com.gve.testapplication.core.presentation.recyclerview.endlesslistscroll;

import android.support.annotation.NonNull;
import android.support.v4.util.Pair;

import com.gve.testapplication.core.presentation.recyclerview.DisplayableItem;
import com.gve.testapplication.core.presentation.recyclerview.RecyclerViewConstant;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.subjects.BehaviorSubject;
import polanski.option.Option;

import static com.gve.testapplication.core.presentation.recyclerview.DisplayableItem.toDisplayableItem;

/**
 * Created by gve on 28/11/2017.
 */

public class EndlessListDomainLogic<T> {

    private CompositeDisposable disposable = new CompositeDisposable();
    private Function<List<T>, List<DisplayableItem>> mapper;
    private RepoInfiniteScrolling<T> repo;
    private BehaviorSubject<Pair<Option<Throwable>, List<DisplayableItem>>> listBehaviorSubject =
            BehaviorSubject.createDefault(new Pair<>(Option.none(), new ArrayList<DisplayableItem>()));

    private int numPage = 1;

    @Inject
    public EndlessListDomainLogic(@NonNull Function<List<T>, List<DisplayableItem>> mapper,
                                  @NonNull RepoInfiniteScrolling<T> repo) {

        this.mapper = mapper;
        this.repo = repo;
        fetch();
    }

    public void fetch() {
        if (listBehaviorSubject.getValue().second.size() > 0) {
            listBehaviorSubject.onNext(
                    new Pair<>(Option.none(),
                            addProgressEmptyRow(listBehaviorSubject.getValue().second)));
        }
        disposable.add(repo.get(numPage)
                .map(mapper)
                .map(list -> {
                    List<DisplayableItem> current =
                            removeLastItemIfEmptyType(listBehaviorSubject.getValue().second);
                    current.addAll(list);
                    return current;
                })
                .subscribe(list -> {
                    listBehaviorSubject.onNext(new Pair<>(Option.none(), list));
                    numPage++;
                    }, error -> listBehaviorSubject.onNext(
                        new Pair<>(Option.ofObj(error),
                                removeLastItemIfEmptyType(listBehaviorSubject.getValue().second)))));
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

    public Flowable<Pair<Option<Throwable>, List<DisplayableItem>>> getDisplayableList() {
        return listBehaviorSubject.toFlowable(BackpressureStrategy.BUFFER);
    }
}
