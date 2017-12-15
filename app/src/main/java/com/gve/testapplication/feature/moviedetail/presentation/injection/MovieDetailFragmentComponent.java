package com.gve.testapplication.feature.moviedetail.presentation.injection;

import com.gve.testapplication.core.injection.SubcomponentBuilder;
import com.gve.testapplication.core.injection.scopes.FragmentScope;
import com.gve.testapplication.feature.moviedetail.presentation.MovieDetailFragment;

import dagger.Subcomponent;

/**
 * Created by gve on 15/12/2017.
 */

@FragmentScope
@Subcomponent()
public interface MovieDetailFragmentComponent {

    @Subcomponent.Builder
    interface Builder extends SubcomponentBuilder<MovieDetailFragmentComponent> {

    }

    void inject(MovieDetailFragment fragment);
}
