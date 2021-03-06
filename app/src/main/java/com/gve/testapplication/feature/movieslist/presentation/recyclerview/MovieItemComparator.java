package com.gve.testapplication.feature.movieslist.presentation.recyclerview;

import com.gve.testapplication.core.presentation.recyclerview.DisplayableItem;
import com.gve.testapplication.core.presentation.recyclerview.ItemComparator;

/**
 * Created by gve on 27/10/2017.
 */

public final class MovieItemComparator
        implements ItemComparator {

    @Override
    public boolean areItemsTheSame(final DisplayableItem item1, final DisplayableItem item2) {
        return item1.equals(item2);
    }

    @Override
    public boolean areContentsTheSame(final DisplayableItem item1, final DisplayableItem item2) {
        return item1.equals(item2);
    }
}
