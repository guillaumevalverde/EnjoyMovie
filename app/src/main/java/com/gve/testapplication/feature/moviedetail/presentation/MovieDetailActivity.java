package com.gve.testapplication.feature.moviedetail.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.gve.testapplication.R;
import com.gve.testapplication.core.BootCampApp;
import com.gve.testapplication.core.injection.activity.BaseInjectingActivity;
import com.gve.testapplication.feature.Movie;
import com.gve.testapplication.feature.data.MovieDetailPagerViewModel;
import com.gve.testapplication.feature.data.MovieDetailRepo;
import com.gve.testapplication.feature.moviedetail.presentation.injection.MovieDetailActivityComponent;
import com.gve.testapplication.feature.moviedetail.presentation.injection.MovieDetailActivityModule;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;


/**
 * Created by gve on 15/12/2017.
 */

public class MovieDetailActivity extends BaseInjectingActivity<MovieDetailActivityComponent> {
    public static final String TAG = MovieDetailActivity.class.getSimpleName();
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String URL = "url";
    public static final String VOTE = "vote";

    @Inject
    MovieDetailPagerViewModel.FactoryPagerViewModel factoryPagerViewModel;
    @Inject
    MovieDetailRepo repo;
    @Inject
    Picasso picasso;
    private InfiniteViewPager pager;

    private CompositeDisposable disposable = new CompositeDisposable();

    public static Movie getMovieFromIntent(Intent intent) {
        return new Movie(intent.getLongExtra(ID, -1),
                intent.getStringExtra(NAME),
                intent.getFloatExtra(VOTE, 0),
                intent.getStringExtra(URL));
    }

    public static void setMovieFromIntent(Intent intent, Movie movie) {
        intent.putExtra(ID, movie.getId());
        intent.putExtra(NAME, movie.getName());
        intent.putExtra(VOTE, movie.getVote());
        intent.putExtra(URL, movie.getUrl());
    }

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Movie movieRef = getMovieFromIntent(getIntent());
        Log.v(TAG, "Movie detail: " + movieRef.toString());
        getSupportActionBar().setTitle(this.getResources().getString(R.string.app_name));
        pager = findViewById(R.id.movie_detail_pager);

        InfiniteViewPagerAdapter pagerAdapter =
                new InfiniteViewPagerAdapter(getBaseContext(), movieRef, repo, picasso);
        disposable.add(factoryPagerViewModel.getViewModel(movieRef).getMovie().subscribe(
                movies -> {
                    pagerAdapter.addRessource(movies);
                    pager.setCurrentItem(0);
                }, error -> Log.e(TAG, "error: " + error.getMessage())));

        pager.setAdapter(pagerAdapter);
    }

    @Override
    protected void onInject(@NonNull MovieDetailActivityComponent movieDetailActivityComponent) {
        movieDetailActivityComponent.inject(this);
    }

    @NonNull
    @Override
    protected MovieDetailActivityComponent createComponent() {
        MovieDetailActivityComponent.Builder builder = (MovieDetailActivityComponent.Builder)
                (((BootCampApp) getApplication()).getComponent())
                        .subComponentBuilders()
                        .get(MovieDetailActivityComponent.Builder.class)
                        .get();
        return builder.activityModule(new MovieDetailActivityModule(this)).build();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_movie_detail;
    }

}
