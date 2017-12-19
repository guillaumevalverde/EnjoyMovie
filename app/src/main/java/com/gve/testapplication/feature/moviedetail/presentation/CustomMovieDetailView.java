package com.gve.testapplication.feature.moviedetail.presentation;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gve.testapplication.R;
import com.gve.testapplication.core.utils.PicassoUtils;
import com.gve.testapplication.feature.data.Movie;
import com.gve.testapplication.feature.data.MovieDetail;

import com.gve.testapplication.feature.moviedetail.data.MovieDetailRepo;
import com.squareup.picasso.Picasso;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by gve on 17/12/2017.
 */

public class CustomMovieDetailView extends RelativeLayout {

    private static final String TAG = CustomMovieDetailView.class.getSimpleName();
    private MovieDetailRepo repo;
    private TextView movieOverViewTV;
    private TextView movieBudgetTV;
    private TextView movieRevenueTV;
    private TextView movieReleaseDateTV;
    private TextView movieOriginalLanguageTV;
    private ProgressBar progressBar;
    private TextView emptyTv;
    private Movie movieRef;
    private CompositeDisposable disposable = new CompositeDisposable();
    private Picasso picasso;

    public CustomMovieDetailView(Context context, Picasso picasso, Movie movie, MovieDetailRepo repo) {
        super(context);
        this.repo = repo;
        this.movieRef = movie;
        this.picasso = picasso;
        init(context);
    }

    public CustomMovieDetailView(Context context) {
        super(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_detail,
                this, true);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        setLayoutParams(layoutParams);
        ImageView movieIV = view.findViewById(R.id.movie_detail_image);
        TextView movieTitleTV = view.findViewById(R.id.movie_detail_title);
        movieOverViewTV = view.findViewById(R.id.movie_detail_overview);
        movieBudgetTV = view.findViewById(R.id.movie_detail_budget);
        movieRevenueTV = view.findViewById(R.id.movie_detail_revenue);
        movieReleaseDateTV = view.findViewById(R.id.movie_detail_release_date);
        movieOriginalLanguageTV = view.findViewById(R.id.movie_detail_original_language);
        TextView movieVoteTV = view.findViewById(R.id.movie_detail_vote);
        emptyTv = findViewById(R.id.movie_detail_empty);
        progressBar = findViewById(R.id.movie_detail_progress_bar);

        movieTitleTV.setText(movieRef.getName());
        movieVoteTV.setText(movieVoteTV.getResources()
                .getString(R.string.movie_vote, movieRef.getVote()));

        PicassoUtils.showImageWithPicasso(picasso, movieIV, movieRef.getUrl());

        disposable.add(repo.get(movieRef.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::updateDetail,
                        error -> onErrorDisplay(error)));
    }

    public void onErrorDisplay(Throwable error) {
        emptyTv.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        Log.e(TAG, "error: " + error.getMessage());
    }

    private void updateDetail(MovieDetail moviedetail) {
        movieOverViewTV.setText(moviedetail.getOverview());
        movieBudgetTV.setText(movieBudgetTV.getResources()
                .getString(R.string.movie_detail_budget, moviedetail.getBudget()));
        movieRevenueTV.setText(movieBudgetTV.getResources()
                .getString(R.string.movie_detail_revenue, moviedetail.getRevenue()));
        movieReleaseDateTV.setText(movieBudgetTV.getResources()
                .getString(R.string.movie_detail_release_date, moviedetail.getRelease_date()));
        movieOriginalLanguageTV.setText(movieBudgetTV.getResources()
                .getString(R.string.movie_detail_original_language, moviedetail.getOriginal_language()));
        emptyTv.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        disposable.clear();
    }
}
