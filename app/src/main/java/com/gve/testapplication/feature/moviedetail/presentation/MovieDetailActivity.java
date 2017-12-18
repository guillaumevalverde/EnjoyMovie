package com.gve.testapplication.feature.moviedetail.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.transition.Transition;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gve.testapplication.R;
import com.gve.testapplication.core.BootCampApp;
import com.gve.testapplication.core.injection.activity.BaseInjectingActivity;
import com.gve.testapplication.core.presentation.InfiniteViewPager;
import com.gve.testapplication.core.utils.PicassoUtils;
import com.gve.testapplication.feature.Movie;
import com.gve.testapplication.feature.data.MovieDetailPagerRepo;
import com.gve.testapplication.feature.data.MovieDetailRepo;
import com.gve.testapplication.feature.moviedetail.presentation.injection.MovieDetailActivityComponent;
import com.gve.testapplication.feature.moviedetail.presentation.injection.MovieDetailActivityModule;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


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
    MovieDetailPagerRepo.FactoryMovieDetailPagerRepo factoryPagerViewModel;

    @Inject
    MovieDetailRepo repo;

    @Inject
    Picasso picasso;

    private InfiniteViewPager pager;

    private CompositeDisposable disposable = new CompositeDisposable();

    private Handler handler = new Handler();

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

        ImageView target = findViewById(R.id.movie_detail_transition_image);
        TextView targetTV = findViewById(R.id.movie_detail_transition_title);
        LinearLayout starLL = findViewById(R.id.movie_detail_transition_ll);
        targetTV.setText(movieRef.getName());
        supportPostponeEnterTransition();

        PicassoUtils.showImageWithPicasso(picasso, target, movieRef.getUrl(), new Callback() {
            @Override
            public void onSuccess() {
                scheduleStartPostponedTransition(target);
            }

            @Override
            public void onError() {
            }
        });


        final InfiniteViewPagerAdapter pagerAdapter =
                new InfiniteViewPagerAdapter(getBaseContext(), new ArrayList<>(), repo, picasso);

        disposable.add(factoryPagerViewModel.getViewModel(movieRef).getMovieWithSimilar()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                movies -> {
                    pagerAdapter.addRessourceWithouNotify(movies);
                    pager.setAdapter(pagerAdapter);
                }, error -> Log.e(TAG, "error: " + error.getMessage())));


        getWindow().getSharedElementEnterTransition().addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                Log.d(TAG, "onTransitionStart");
                handler.post(() -> {
                    target.setVisibility(View.VISIBLE);
                    targetTV.setVisibility(View.VISIBLE);
                    starLL.setVisibility(View.VISIBLE);
                });
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                Log.d(TAG, "onTransitionEnd");
                handler.post(() -> {
                    target.setVisibility(View.INVISIBLE);
                    targetTV.setVisibility(View.INVISIBLE);
                    starLL.setVisibility(View.INVISIBLE);
                });
            }

            @Override
            public void onTransitionCancel(Transition transition) {
                Log.d(TAG, "onTransitionCancel");
                handler.post(() -> {
                    target.setVisibility(View.INVISIBLE);
                    targetTV.setVisibility(View.INVISIBLE);
                    starLL.setVisibility(View.INVISIBLE);
                });
            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {
                Log.v(TAG, "onTransitionResume");
                handler.post(() -> {
                    target.setVisibility(View.VISIBLE);
                    targetTV.setVisibility(View.VISIBLE);
                    starLL.setVisibility(View.VISIBLE);
                });
            }
        });
    }

    private void scheduleStartPostponedTransition(final View sharedElement) {
        sharedElement.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        sharedElement.getViewTreeObserver().removeOnPreDrawListener(this);
                        startPostponedEnterTransition();
                        return true;
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
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
