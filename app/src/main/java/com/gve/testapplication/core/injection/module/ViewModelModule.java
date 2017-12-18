package com.gve.testapplication.core.injection.module;

import android.arch.lifecycle.ViewModelProvider;

import com.gve.testapplication.core.presentation.recyclerview.endlesslistscroll.EndlessListDomainLogic;
import com.gve.testapplication.feature.Movie;
import com.gve.testapplication.feature.data.ListMovieRepo;
import com.gve.testapplication.feature.movieslist.presentation.LifeCycleMovieViewModel;
import com.gve.testapplication.feature.movieslist.presentation.recyclerview.MovieDisplayableMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by gve on 18/12/2017.
 */

@Module
public final class ViewModelModule {

    @Provides
    public EndlessListDomainLogic<Movie> getEndless(MovieDisplayableMapper mapper, ListMovieRepo repo) {
        return new EndlessListDomainLogic<Movie>(mapper, repo);
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory getViewModelProvider(EndlessListDomainLogic<Movie> endlessLogic) {
        return new LifeCycleMovieViewModel.LifeCycleMovieViewModelFactory(endlessLogic);
    }
}
