package com.gve.testapplication.core.injection.module;

import com.gve.testapplication.feature.presentation.injection.LifeCycleMovieListModule;

import dagger.Module;

@Module(includes = {LifeCycleMovieListModule.class})
public final class ViewModelModule {
}
