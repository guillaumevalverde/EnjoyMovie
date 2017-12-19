package com.gve.testapplication.feature.movieslist.presentation;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gve.testapplication.R;
import com.gve.testapplication.core.BootCampApp;
import com.gve.testapplication.core.injection.activity.BaseInjectingActivity;
import com.gve.testapplication.core.injection.qualifiers.ForActivity;
import com.gve.testapplication.core.presentation.recyclerview.DisplayableItem;
import com.gve.testapplication.core.presentation.recyclerview.RecyclerViewAdapter;
import com.gve.testapplication.core.presentation.recyclerview.endlesslistscroll.EndlessListDomainLogic;
import com.gve.testapplication.core.presentation.recyclerview.endlesslistscroll.EndlessScrollListenerDelegate;
import com.gve.testapplication.core.utils.ConnectivityReceiver;
import com.gve.testapplication.feature.data.Movie;
import com.gve.testapplication.feature.movieslist.presentation.injection.ListMovieActivityComponent;
import com.gve.testapplication.feature.movieslist.presentation.injection.ListMovieActivityModule;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import polanski.option.Option;

/**
 * Created by gve on 31/10/2017.
 */

public class ListMovieActivity extends BaseInjectingActivity<ListMovieActivityComponent>
        implements ConnectivityReceiver.ConnectivityReceiverListener {
    public static final String TAG = ListMovieActivity.class.getSimpleName();

    @Inject
    RecyclerViewAdapter adapter;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Inject
    @ForActivity
    Context context;

    @Inject
    EndlessListDomainLogic<Movie> endlessListMovieLogic;

    private LifeCycleMovieViewModel viewModel;

    private CompositeDisposable disposable = new CompositeDisposable();
    private Option<Throwable> potentialError = Option.none();
    private ConnectivityReceiver networkBroadcastReceiver;
    private TextView emptyTV;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this,
                viewModelFactory).get(LifeCycleMovieViewModel.class);

        setContentView(R.layout.activity_movie_list);
        emptyTV = findViewById(R.id.activity_movie_empty);
        emptyTV.setVisibility(View.INVISIBLE);
        progressBar = findViewById(R.id.activity_movie_progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        getSupportActionBar().setTitle(context.getResources().getString(R.string.app_name));

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(),
                        DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);


        EndlessScrollListenerDelegate listener =
                new EndlessScrollListenerDelegate(viewModel.getCallableFetch());

        recyclerView.addOnScrollListener(listener);
        RecyclerView.LayoutManager mLayoutManager
                = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        networkBroadcastReceiver = new ConnectivityReceiver(this);
        networkBroadcastReceiver.registerReceiver(this);

        viewModel.getRepositoryListLiveData().observe(this, stateItems -> {
            updateInfo(stateItems);
        });
    }

    private void updateInfo(Pair<Option<Throwable>, List<DisplayableItem>> state) {

        this.potentialError = state.first;
        potentialError.ifSome(error -> {
                error.printStackTrace();
                Toast.makeText(this, getResources().getString(R.string.network_issue),
                        Toast.LENGTH_SHORT).show();
        });
        adapter.update(state.second);
        emptyTV.setVisibility(adapter.getItemCount() == 0 ? View.VISIBLE : View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
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
        return R.layout.activity_movie_list;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        networkBroadcastReceiver.unregisterReceiver(this);
        disposable.clear();
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
       Log.v(TAG, "onNetworkConnectionChanged: " + isConnected);
        if (isConnected) {

            potentialError.ifSome(error -> {
                try {
                    Log.v(TAG, "fetch: " + isConnected);
                    if (adapter.getItemCount() == 0) {
                        progressBar.setVisibility(View.VISIBLE);
                        emptyTV.setVisibility(View.INVISIBLE);
                    }
                    viewModel.fetch();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
