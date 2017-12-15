package com.gve.testapplication.core.injection.module;

import com.gve.testapplication.core.injection.SubcomponentKey;
import com.gve.testapplication.core.injection.SubcomponentBuilder;
import com.gve.testapplication.feature.moviedetail.presentation.injection.MovieDetailActivityComponent;
import com.gve.testapplication.feature.movieslist.presentation.injection.ListMovieActivityComponent;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by gve.
 */

@Module(subcomponents = {ListMovieActivityComponent.class, MovieDetailActivityComponent.class})

public abstract class ActivityBindingModule {

    @Binds @IntoMap
    @SubcomponentKey(ListMovieActivityComponent.Builder.class)
    public abstract SubcomponentBuilder listMovieActivity(ListMovieActivityComponent.Builder impl);

    @Binds @IntoMap
    @SubcomponentKey(MovieDetailActivityComponent.Builder.class)
    public abstract SubcomponentBuilder movieDetailActivity(MovieDetailActivityComponent.Builder impl);

}
