package com.gve.testapplication.feature.movieslist.presentation.injection;

import com.gve.testapplication.core.preconditions.AndroidPreconditions;
import com.gve.testapplication.core.recyclerview.EmptyViewHolder;
import com.gve.testapplication.core.recyclerview.ItemComparator;
import com.gve.testapplication.core.recyclerview.RecyclerViewAdapter;
import com.gve.testapplication.core.recyclerview.ViewHolderBinder;
import com.gve.testapplication.core.recyclerview.ViewHolderFactory;
import com.gve.testapplication.feature.movieslist.presentation.recyclerview.MovieItemComparator;
import com.gve.testapplication.feature.movieslist.presentation.recyclerview.MovieViewHolder;

import java.util.Map;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntKey;
import dagger.multibindings.IntoMap;

import static com.gve.testapplication.core.recyclerview.RecyclerViewConstant.EMPTY_TYPE;
import static com.gve.testapplication.core.recyclerview.RecyclerViewConstant.MOVIE_CARD_TYPE;

/**
 * Created by gve on 26/10/2017.
 */

@Module
public abstract class RecyclerViewMovieListModule {

    @Provides
    static RecyclerViewAdapter provideRecyclerAdapter(ItemComparator itemComparator,
                                                      Map<Integer, ViewHolderFactory> factoryMap,
                                                      Map<Integer, ViewHolderBinder> binderMap,
                                                      AndroidPreconditions androidPreconditions) {
        return new RecyclerViewAdapter(itemComparator, factoryMap, binderMap, androidPreconditions);
    }

    @Provides
    static ItemComparator provideComparator() {
        return new MovieItemComparator();
    }

    @Binds
    @IntoMap
    @IntKey(MOVIE_CARD_TYPE)
    abstract ViewHolderFactory
        provideMovieCardHolderFactory(MovieViewHolder.MovieCardHolderFactory factory);

    @Binds
    @IntoMap
    @IntKey(MOVIE_CARD_TYPE)
    abstract ViewHolderBinder
        provideMovieCardHolderBinder(MovieViewHolder.MovieCardHolderBinder binder);

    @Binds
    @IntoMap
    @IntKey(EMPTY_TYPE)
    abstract ViewHolderFactory provideEmptyCardHolderFactory(EmptyViewHolder.EmptyCardHolderFactory factory);

    @Binds
    @IntoMap
    @IntKey(EMPTY_TYPE)
    abstract ViewHolderBinder
    provideEmptyCardHolderBinder(EmptyViewHolder.EmptyCardHolderBinder binder);
}
