package com.gve.testapplication.feature.movieslist.presentation;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.gve.testapplication.R;
import com.gve.testapplication.core.BootCampApp;
import com.gve.testapplication.core.injection.activity.BaseInjectingActivity;
import com.gve.testapplication.core.injection.qualifiers.ForActivity;
import com.gve.testapplication.core.recyclerview.RecyclerViewAdapter;
import com.gve.testapplication.core.ui.EndlessScrollListenerDelegate;
import com.gve.testapplication.feature.movieslist.presentation.injection.ListMovieActivityComponent;
import com.gve.testapplication.feature.movieslist.presentation.injection.ListMovieActivityModule;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by gve on 31/10/2017.
 */

public class ListMovieActivity extends BaseInjectingActivity<ListMovieActivityComponent> {
    public static final String TAG = ListMovieActivity.class.getSimpleName();

    @Inject
    RecyclerViewAdapter adapter;

    @Inject
    @Named("movie")
    ViewModelProvider.Factory viewModelFactory;

    @Inject
    @ForActivity
    Context context;

    private CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final LifeCycleMovieViewModel viewModel = ViewModelProviders.of(this, viewModelFactory).get(LifeCycleMovieViewModel.class);

        setContentView(R.layout.repository_list);

        getSupportActionBar().setTitle(context.getResources().getString(R.string.app_name));

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(),
                        DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);


        EndlessScrollListenerDelegate listener =
                new EndlessScrollListenerDelegate(viewModel.callableFetch());

        recyclerView.addOnScrollListener(listener);
        RecyclerView.LayoutManager mLayoutManager
                = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        viewModel.getRepositoryListLiveData().observe(this, adapter::update);
    }

    @Override
    protected void onInject(@NonNull ListMovieActivityComponent listMovieActivityComponent) {
        listMovieActivityComponent.inject(this);
    }

    @NonNull
    @Override
    protected ListMovieActivityComponent createComponent() {
        ListMovieActivityComponent.Builder builder = (ListMovieActivityComponent.Builder)
                (((BootCampApp) getApplication()).getComponent())
                        .subComponentBuilders()
                        .get(ListMovieActivityComponent.Builder.class)
                        .get();
        return builder.activityModule(new ListMovieActivityModule(this)).build();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.repository_list;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }
}
