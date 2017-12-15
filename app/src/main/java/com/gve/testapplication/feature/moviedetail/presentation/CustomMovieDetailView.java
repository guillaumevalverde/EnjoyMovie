package com.gve.testapplication.feature.moviedetail.presentation;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gve.testapplication.R;
import com.gve.testapplication.core.utils.PicassoUtils;
import com.gve.testapplication.feature.Movie;
import com.gve.testapplication.feature.MovieDetail;
import com.gve.testapplication.feature.data.MovieDetailRepo;
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
    private Context context;
    private ImageView movieIV;
    private TextView movieTitleTV;
    private TextView movieOverViewTV;
    private Movie movieRef;
    private CompositeDisposable disposable = new CompositeDisposable();
    private Picasso picasso;

    public CustomMovieDetailView(Context context, Picasso picasso, Movie movie, MovieDetailRepo repo) {
        super(context);
        this.repo = repo;
        this.context = context;
        this.movieRef = movie;
        this.picasso = picasso;
        init(context);
    }

    public CustomMovieDetailView(Context context, AttributeSet attrs, MovieDetailRepo repo) {
        super(context, attrs);
        init(context);
        this.repo = repo;
        this.context = context;
    }

    public CustomMovieDetailView(Context context, AttributeSet attrs, int defStyleAttr,
                                 MovieDetailRepo repo) {
        super(context, attrs, defStyleAttr);
        init(context);
        this.repo = repo;
        this.context = context;
    }


    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_movie_detail,
                this, true);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        setLayoutParams(layoutParams);
        movieIV = view.findViewById(R.id.movie_detail_image);
        movieTitleTV = view.findViewById(R.id.movie_detail_title);
        movieOverViewTV = view.findViewById(R.id.movie_detail_overview);
        movieTitleTV.setText(movieRef.getName());
        PicassoUtils.showImageWithPicasso(picasso, movieIV, movieRef.getUrl());

        disposable.add(repo.fetch(movieRef.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::updateDetail,
                        error -> Log.e(TAG, "error: " + error.getMessage())));

    }

    public CustomMovieDetailView(Context context) {
        super(context);
    }

    private void updateMovie(Movie movie) {
        //PicassoUtils.showImageWithPicasso(picasso, movieIV, movie.getUrl());
        movieTitleTV.setText(movie.getName());
    }

    private void updateDetail(MovieDetail moviedetail) {
        movieOverViewTV.setText(moviedetail.getOverview());
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        disposable.clear();
    }
}
