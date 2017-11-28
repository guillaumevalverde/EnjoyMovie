package com.gve.testapplication.ListOfRepoFeature.presentation;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.gve.testapplication.ListOfRepoFeature.presentation.injection.ListRepoActivityComponent;
import com.gve.testapplication.ListOfRepoFeature.presentation.injection.ListRepoActivityModule;
import com.gve.testapplication.R;
import com.gve.testapplication.core.BootCampApp;
import com.gve.testapplication.core.injection.qualifiers.ForActivity;
import com.gve.testapplication.core.recyclerview.RecyclerViewAdapter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by gve on 31/10/2017.
 */

public class ListRepositoryActivity extends AppCompatActivity {
    public static final String TAG = ListRepositoryActivity.class.getSimpleName();

    @Inject
    RecyclerViewAdapter adapter;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Inject
    @ForActivity
    Context context;

    private CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ListRepoActivityComponent.Builder builder = (ListRepoActivityComponent.Builder)
                (((BootCampApp) getApplication()).getComponent())
                        .subComponentBuilders()
                        .get(ListRepoActivityComponent.Builder.class)
                        .get();
        builder.activityModule(new ListRepoActivityModule(this)).build().inject(this);

        super.onCreate(savedInstanceState);

        final LifeCycleViewModel viewModel = ViewModelProviders.of(this, viewModelFactory).get(LifeCycleViewModel.class);

        setContentView(R.layout.repository_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView titleToolBar = toolbar.findViewById(R.id.toolbar_title);
        titleToolBar.setText(context.getResources().getString(R.string.app_name));

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        EndlessScrollListenerDelegate listener =
                new EndlessScrollListenerDelegate(viewModel.callableFetch());

        recyclerView.addOnScrollListener(listener);
        RecyclerView.LayoutManager mLayoutManager
                = new LinearLayoutManager(getApplicationContext());

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(),
                        DividerItemDecoration.VERTICAL);

        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        viewModel.getRepositoryListLiveData().observe(this, adapter::update);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }
}
