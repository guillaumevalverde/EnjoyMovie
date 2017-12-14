package com.gve.testapplication.ListOfRepoFeature.presentation.injection;

import android.arch.lifecycle.ViewModelProvider;

import com.gve.testapplication.ListOfRepoFeature.presentation.LifeCycleViewModel;
import com.gve.testapplication.core.utils.ViewModelUtil;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by gve on 29/11/2017.
 */

@Module
public class LifeCycleRepoListModule {
    @Singleton
    @Provides
    @Named("repo")
    static ViewModelProvider.Factory provideViewModelProviderFactory(ViewModelUtil viewModelUtil, LifeCycleViewModel viewModel) {
        return viewModelUtil.createFor(viewModel);
    }
}
