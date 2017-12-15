package com.gve.testapplication.feature.moviedetail.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gve.testapplication.R;
import com.gve.testapplication.core.injection.fragment.BaseInjectingFragment;
import com.gve.testapplication.core.utils.PicassoUtils;
import com.gve.testapplication.feature.Movie;
import com.gve.testapplication.feature.MovieDetail;
import com.gve.testapplication.feature.data.MovieDetailRepo;
import com.gve.testapplication.feature.moviedetail.presentation.injection.MovieDetailFragmentComponent;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static com.gve.testapplication.feature.moviedetail.presentation.MovieDetailActivity.ID;
import static com.gve.testapplication.feature.moviedetail.presentation.MovieDetailActivity.NAME;
import static com.gve.testapplication.feature.moviedetail.presentation.MovieDetailActivity.URL;
import static com.gve.testapplication.feature.moviedetail.presentation.MovieDetailActivity.VOTE;


/**
 * Created by gve on 15/12/2017.
 */

public class MovieDetailFragment extends BaseInjectingFragment {
    private static final String TAG = MovieDetailFragment.class.getSimpleName();

    @Inject
    Picasso picasso;

    @Inject
    MovieDetailRepo repo;

    private Movie movieRef;
    private CompositeDisposable disposable = new CompositeDisposable();

    private ImageView movieIV;
    private TextView movieTitleTV;
    private TextView movieOverViewTV;

    public static MovieDetailFragment newInstance(Movie movie) {
        MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
        Bundle args = setMovieInBundle(movie);
        movieDetailFragment.setArguments(args);
        return movieDetailFragment;
    }


    public static Movie getMovieFromBundle(Bundle bundle) {
        if (bundle == null)
            return null;
        else {
            return new Movie(bundle.getLong(ID, -1),
                    bundle.getString(NAME),
                    bundle.getFloat(VOTE, 0),
                    bundle.getString(URL));
        }
    }

    public static Bundle setMovieInBundle(Movie movie) {
        Bundle bundle = new Bundle();
        bundle.putLong(ID, movie.getId());
        bundle.putString(NAME, movie.getName());
        bundle.putFloat(VOTE, movie.getVote());
        bundle.putString(URL, movie.getUrl());
        return bundle;
    }

    private void updateMovie(Movie movie) {
        PicassoUtils.showImageWithPicasso(picasso, movieIV, movie.getUrl());
        movieTitleTV.setText(movie.getName());
    }

    private void updateDetail(MovieDetail moviedetail) {
        movieOverViewTV.setText(moviedetail.getOverview());
    }

    @Override
    public void onInject() {

        MovieDetailFragmentComponent.Builder builder = (MovieDetailFragmentComponent.Builder)
                (((MovieDetailActivity) getActivity()).getComponent())
                        .subComponentBuilders()
                        .get(MovieDetailFragmentComponent.Builder.class)
                        .get();
        builder.build().inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieRef = getMovieFromBundle(getArguments());
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        movieIV = view.findViewById(R.id.movie_detail_image);
        movieTitleTV = view.findViewById(R.id.movie_detail_title);
        movieOverViewTV = view.findViewById(R.id.movie_detail_overview);
        updateMovie(movieRef);

        Log.v("gui", "movieRef: " + movieRef.toString());
        disposable.add(repo.fetch(movieRef.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::updateDetail,
                        error -> Log.e(TAG, "error: " + error.getMessage())));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_movie_detail;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }

}
