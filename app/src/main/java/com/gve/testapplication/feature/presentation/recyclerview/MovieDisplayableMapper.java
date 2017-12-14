package com.gve.testapplication.feature.presentation.recyclerview;

import android.support.annotation.NonNull;

import com.gve.testapplication.core.recyclerview.DisplayableItem;
import com.gve.testapplication.core.recyclerview.RecyclerViewConstant;
import com.gve.testapplication.feature.presentation.Movie;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

import static com.gve.testapplication.core.recyclerview.DisplayableItem.toDisplayableItem;

/**
 * Created by gve on 27/10/2017.
 */

public class MovieDisplayableMapper implements Function<List<Movie>, List<DisplayableItem>> {

    @Inject
    public MovieDisplayableMapper() { }

    @Override
    public List<DisplayableItem> apply(@NonNull final List<Movie> repositories)
            throws Exception {
        return Observable.fromIterable(repositories)
                .map(this::wrapInDisplayableItem)
                .toList()
                .blockingGet();
    }

    private DisplayableItem wrapInDisplayableItem(Movie viewEntity) {
        return toDisplayableItem(viewEntity, RecyclerViewConstant.MOVIE_CARD_TYPE);
    }
}
