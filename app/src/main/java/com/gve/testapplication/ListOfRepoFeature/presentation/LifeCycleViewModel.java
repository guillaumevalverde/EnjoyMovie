package com.gve.testapplication.ListOfRepoFeature.presentation;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import com.gve.testapplication.ListOfRepoFeature.domain.EndlessListRepoLogic;
import com.gve.testapplication.core.recyclerview.DisplayableItem;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by gve on 29/11/2017.
 */

public class LifeCycleViewModel extends ViewModel {
    private static final String TAG = LifeCycleViewModel.class.getSimpleName();

    @NonNull
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final MutableLiveData<List<DisplayableItem>> repositoryListLiveData = new MutableLiveData<>();

    private EndlessListRepoLogic viewModel;

    @Inject
    LifeCycleViewModel(@NonNull final EndlessListRepoLogic viewModel) {
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

    public Callable callableFetch() {
        return viewModel.callableFetch();
    }
}
