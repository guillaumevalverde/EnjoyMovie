package com.gve.testapplication.feature.moviedetail.presentation.injection;
import com.gve.testapplication.core.injection.SubcomponentBuilder;
import com.gve.testapplication.core.injection.module.FragmentBindingModule;
import com.gve.testapplication.core.injection.scopes.ActivityScope;
import com.gve.testapplication.feature.moviedetail.presentation.MovieDetailActivity;

import java.util.Map;

import javax.inject.Provider;

import dagger.Subcomponent;

/**
 * Created by gve on 15/12/2017.
 */

@ActivityScope
@Subcomponent(modules = {FragmentBindingModule.class, MovieDetailActivityModule.class})

public interface MovieDetailActivityComponent {

    @Subcomponent.Builder
    interface Builder extends SubcomponentBuilder<MovieDetailActivityComponent> {
        Builder activityModule(MovieDetailActivityModule module);
    }

    Map<Class<?>, Provider<SubcomponentBuilder>> subComponentBuilders();

    void inject(MovieDetailActivity activity);
}
