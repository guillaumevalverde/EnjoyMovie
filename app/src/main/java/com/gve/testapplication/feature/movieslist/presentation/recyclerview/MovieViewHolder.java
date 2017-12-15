package com.gve.testapplication.feature.movieslist.presentation.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.gve.testapplication.R;
import com.gve.testapplication.core.injection.qualifiers.ForActivity;
import com.gve.testapplication.core.recyclerview.DisplayableItem;
import com.gve.testapplication.core.recyclerview.ViewHolderBinder;
import com.gve.testapplication.core.recyclerview.ViewHolderFactory;
import com.gve.testapplication.core.utils.PicassoUtils;
import com.gve.testapplication.feature.movieslist.presentation.Movie;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    private TextView movieNameTV;
    private TextView movieVoteTv;
    private ImageView movieIV;
    private Picasso picasso;

    public MovieViewHolder(final View itemView, Picasso picasso) {
        super(itemView);
        this.picasso = picasso;
        movieNameTV = itemView.findViewById(R.id.movie_card_title);
        movieVoteTv = itemView.findViewById(R.id.movie_card_vote);
        movieIV = itemView.findViewById(R.id.movie_card_image);
    }

    private void bind(@NonNull final Movie movie) {
        movieNameTV.setText(movie.getName());
        movieVoteTv.setText(movieNameTV.getResources()
                .getString(R.string.movie_vote, movie.getVote()));

        PicassoUtils.showImageWithPicasso(picasso, movieIV, movie.getUrl());
        //itemView.setOnLongClickListener(view -> showDialog(movie));
    }

    public static class MovieCardHolderFactory extends ViewHolderFactory {

        private Picasso picasso;

        @Inject
        MovieCardHolderFactory(@NonNull @ForActivity final Context context,  Picasso picasso) {
            super(context);
            this.picasso = picasso;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder createViewHolder(@NonNull final ViewGroup parent) {
            return new MovieViewHolder(LayoutInflater.from(context)
                    .inflate(R.layout.movie_recycler_row, parent, false), picasso);
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
