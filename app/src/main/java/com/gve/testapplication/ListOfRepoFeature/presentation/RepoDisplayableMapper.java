package com.gve.testapplication.ListOfRepoFeature.presentation;

import android.support.annotation.NonNull;

import com.gve.testapplication.ListOfRepoFeature.data.Repository;
import com.gve.testapplication.core.recyclerview.DisplayableItem;
import com.gve.testapplication.core.recyclerview.RecyclerViewConstant;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

import static com.gve.testapplication.core.recyclerview.DisplayableItem.toDisplayableItem;

/**
 * Created by gve on 27/10/2017.
 */

public class RepoDisplayableMapper implements Function<List<Repository>, List<DisplayableItem>> {

    @Inject
    public RepoDisplayableMapper() { }

    @Override
    public List<DisplayableItem> apply(@NonNull final List<Repository> repositories) throws Exception {
        return Observable.fromIterable(repositories)
                .map(this::wrapInDisplayableItem)
                .toList()
                .blockingGet();
    }

    private DisplayableItem wrapInDisplayableItem(Repository viewEntity) {
        return toDisplayableItem(viewEntity, RecyclerViewConstant.REPO_CARD_TYPE);
    }
}
