package com.gve.testapplication.core.presentation.recyclerview.endlesslistscroll;


import java.util.List;

import io.reactivex.Single;

/**
 * Created by gve on 18/12/2017.
 */

public interface RepoInfiniteScrolling<T> {

    Single<List<T>> get(long key);
}
