package com.gve.testapplication.feature.presentation.injection;

import android.arch.lifecycle.ViewModelProvider;

import com.gve.testapplication.core.utils.ViewModelUtil;
import com.gve.testapplication.feature.presentation.LifeCycleMovieViewModel;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by gve on 29/11/2017.
 */

@Module
public class LifeCycleMovieListModule {

    @Singleton
    @Provides
    @Named("movie")
    static ViewModelProvider.Factory provideViewModelProviderFactory(
            ViewModelUtil viewModelUtil,
            LifeCycleMovieViewModel viewModel) {
        return viewModelUtil.createFor(viewModel);
    }
}
