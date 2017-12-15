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
import com.gve.testapplication.feature.moviedetail.presentation.injection.MovieDetailActivityComponent;
import com.gve.testapplication.feature.moviedetail.presentation.injection.MovieDetailActivityModule;


/**
 * Created by gve on 15/12/2017.
 */

public class MovieDetailActivity extends BaseInjectingActivity<MovieDetailActivityComponent> {
    public static final String TAG = MovieDetailActivity.class.getSimpleName();
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String URL = "url";
    public static final String VOTE = "vote";

    private Movie movieRef;

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

        movieRef = getMovieFromIntent(getIntent());
        Log.v(TAG, "Movie detail: " + movieRef.toString());
        Toolbar toolbar = findViewById(R.id.movie_detail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView titleToolBar = toolbar.findViewById(R.id.movie_detail_toolbar_title);
        titleToolBar.setText(this.getResources().getString(R.string.app_name));
        showMovieDetailListFragment(movieRef);
    }

    @Override
    protected void onInject(@NonNull MovieDetailActivityComponent musicActivityComponent) {
        musicActivityComponent.inject(this);
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

    private void showMovieDetailListFragment(Movie movie) {
        Log.v(TAG, "showSongListFragment: " + movie.getId());
        MovieDetailFragment fragment = MovieDetailFragment.newInstance(movie);
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.movie_detail_frame, fragment)
                .commit();
    }

    public void onClick(Movie movie, int position) {
        //TODO
    }

}
