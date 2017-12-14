package com.gve.testapplication.core.injection.module;

import com.gve.testapplication.ListOfRepoFeature.presentation.injection.LifeCycleRepoListModule;
import com.gve.testapplication.feature.presentation.injection.LifeCycleMovieListModule;

import dagger.Module;

@Module(includes = {LifeCycleRepoListModule.class, LifeCycleMovieListModule.class})
public final class ViewModelModule {
}
