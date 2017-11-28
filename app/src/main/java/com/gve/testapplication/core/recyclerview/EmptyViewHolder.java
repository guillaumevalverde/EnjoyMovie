package com.gve.testapplication.core.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.gve.testapplication.R;
import com.gve.testapplication.core.injection.qualifiers.ForActivity;

import javax.inject.Inject;

public class EmptyViewHolder extends RecyclerView.ViewHolder {


    public EmptyViewHolder(final View itemView) {
        super(itemView);
    }

    public static class EmptyCardHolderFactory extends ViewHolderFactory {

        @Inject
        EmptyCardHolderFactory(@NonNull @ForActivity final Context context) {
            super(context);
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder createViewHolder(@NonNull final ViewGroup parent) {
            return new EmptyViewHolder(LayoutInflater.from(context)
                    .inflate(R.layout.empty_recycler_row, parent, false));
        }
    }

    public static class EmptyCardHolderBinder implements ViewHolderBinder {

        @Inject
        EmptyCardHolderBinder() { }

        @Override
        public void bind(@NonNull final RecyclerView.ViewHolder viewHolder,
                         @NonNull final DisplayableItem item) { }
    }
}
