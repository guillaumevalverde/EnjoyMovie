package com.gve.testapplication.feature.movieslist.presentation.recyclerview;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.gve.testapplication.R;
import com.gve.testapplication.core.injection.qualifiers.ForActivity;
import com.gve.testapplication.core.presentation.recyclerview.DisplayableItem;
import com.gve.testapplication.core.presentation.recyclerview.ViewHolderBinder;
import com.gve.testapplication.core.presentation.recyclerview.ViewHolderFactory;
import com.gve.testapplication.core.utils.PicassoUtils;
import com.gve.testapplication.feature.data.Movie;
import com.gve.testapplication.feature.moviedetail.presentation.MovieDetailActivity;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    private TextView movieNameTV;
    private TextView movieVoteTv;
    private ImageView movieIV;
    private ImageView movieStarIV;
    private Picasso picasso;
    private Activity activity;

    public MovieViewHolder(final View itemView, Picasso picasso, Activity activity) {
        super(itemView);
        this.picasso = picasso;
        movieNameTV = itemView.findViewById(R.id.movie_card_title);
        movieVoteTv = itemView.findViewById(R.id.movie_card_vote);
        movieIV = itemView.findViewById(R.id.movie_card_image);
        movieStarIV = itemView.findViewById(R.id.movie_card_star);
        this.activity = activity;
    }

    private void bind(@NonNull final Movie movie) {
        movieNameTV.setText(movie.getName());
        movieVoteTv.setText(movieNameTV.getResources()
                .getString(R.string.movie_vote, movie.getVote()));

        PicassoUtils.showImageWithPicasso(picasso, movieIV, movie.getUrl());
        itemView.setOnClickListener(view -> startMovieDetailActivity(movie));
    }

    private void startMovieDetailActivity(Movie movie) {
        Intent intent = new Intent(itemView.getContext(), MovieDetailActivity.class);
        MovieDetailActivity.setMovieFromIntent(intent, movie);
        Pair<View, String> p1 = Pair.create(movieIV, "profile");
        Pair<View, String> p2 = Pair.create(movieNameTV, "title");
        Pair<View, String> p3 = Pair.create(movieStarIV, "star");
        Pair<View, String> p4 = Pair.create(movieVoteTv, "vote");

        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(activity, p1, p2, p3, p4);

        itemView.getContext().startActivity(intent, options.toBundle());
    }

    public static class MovieCardHolderFactory extends ViewHolderFactory {

        private Picasso picasso;
        private Activity activity;

        @Inject
        MovieCardHolderFactory(@NonNull @ForActivity final Activity activity, Picasso picasso) {
            super(activity);
            this.picasso = picasso;
            this.activity = activity;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder createViewHolder(@NonNull final ViewGroup parent) {
            return new MovieViewHolder(LayoutInflater.from(context)
                    .inflate(R.layout.movie_recycler_row, parent, false), picasso, activity);
        }
    }

    public static class MovieCardHolderBinder implements ViewHolderBinder {

        @Inject
        MovieCardHolderBinder() { }

        @Override
        public void bind(@NonNull final RecyclerView.ViewHolder viewHolder,
                         @NonNull final DisplayableItem item) {
            MovieViewHolder castedViewHolder = MovieViewHolder.class.cast(viewHolder);
            Movie viewEntity = Movie.class.cast(item.model());
            castedViewHolder.bind(viewEntity);
        }
    }
}
