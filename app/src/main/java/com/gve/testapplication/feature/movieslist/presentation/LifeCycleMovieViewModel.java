package com.gve.testapplication.feature.movieslist.presentation;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.util.Log;

import com.gve.testapplication.core.presentation.recyclerview.DisplayableItem;
import com.gve.testapplication.core.presentation.recyclerview.endlesslistscroll.EndlessListDomainLogic;
import com.gve.testapplication.feature.Movie;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by gve on 29/11/2017.
 */

public class LifeCycleMovieViewModel extends ViewModel {
    private static final String TAG = LifeCycleMovieViewModel.class.getSimpleName();

    @NonNull
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final MutableLiveData<List<DisplayableItem>> repositoryListLiveData = new MutableLiveData<>();

    private EndlessListDomainLogic<Movie> viewModel;

    @Inject
    LifeCycleMovieViewModel(@NonNull final EndlessListDomainLogic<Movie> viewModel) {
        // Bind view model
        this.viewModel = viewModel;
        compositeDisposable.add(bindToListRepos());
    }

    @NonNull
    private Disposable bindToListRepos() {
        return viewModel.getDisplayableList()
                .observeOn(Schedulers.computation())
                .subscribe(repositoryListLiveData::postValue,
                        e -> Log.e(TAG, "Error updating credit list live data", e));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }

    @NonNull
    LiveData<List<DisplayableItem>> getRepositoryListLiveData() {
        return repositoryListLiveData;
    }

    public Callable getCallableFetch() {
        return viewModel.getCallableFetch();
    }

    public static class LifeCycleMovieViewModelFactory implements ViewModelProvider.Factory {

        private final EndlessListDomainLogic<Movie> viewModel;

        @Inject
        public LifeCycleMovieViewModelFactory(EndlessListDomainLogic endlessListMovieLogic) {
            this.viewModel = endlessListMovieLogic;
        }

        @NonNull
        @Override
        public LifeCycleMovieViewModel  create(@NonNull Class modelClass) {
            return  new LifeCycleMovieViewModel(viewModel);
        }
    }
}
