package com.gve.testapplication.ListOfRepoFeature.presentation;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gve.testapplication.ListOfRepoFeature.data.Repository;
import com.gve.testapplication.R;
import com.gve.testapplication.core.injection.qualifiers.ForActivity;
import com.gve.testapplication.core.recyclerview.DisplayableItem;
import com.gve.testapplication.core.recyclerview.ViewHolderBinder;
import com.gve.testapplication.core.recyclerview.ViewHolderFactory;

import javax.inject.Inject;

import static com.gve.testapplication.core.utils.IntentUtils.isAvailable;

public class RepositoryViewHolder extends RecyclerView.ViewHolder {

    private TextView repoNameTV;
    private TextView repoDescriptionTv;
    private TextView repoOwnerLoginTv;

    public RepositoryViewHolder(final View itemView) {
        super(itemView);
        repoNameTV = itemView.findViewById(R.id.repo_row_name);
        repoDescriptionTv = itemView.findViewById(R.id.repo_row_description);
        repoOwnerLoginTv = itemView.findViewById(R.id.repo_row_login_owner);
    }

    private void bind(@NonNull final Repository repository) {
        repoNameTV.setText(repoNameTV.getResources().getString(R.string.repo_name,
                repository.getName()));
        repoDescriptionTv.setText(repoDescriptionTv.getResources()
                .getString(R.string.repo_ddescritpion, repository.getDescription()));
        repoOwnerLoginTv.setText(repoOwnerLoginTv.getResources()
                .getString(R.string.repo_owner_login, repository.getLoginOwner()));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            itemView.setBackground(repository.getFork()
                    ? itemView.getContext().getResources()
                    .getDrawable(R.drawable.ripple_effect_fork_true)
                    : itemView.getContext().getResources()
                    .getDrawable(R.drawable.ripple_effect_fork_false));
        } else {
            itemView.setBackgroundColor(repository.getFork()
                    ? itemView.getContext().getResources()
                    .getColor(R.color.background_card_fork_true)
                    : itemView.getContext().getResources()
                    .getColor(R.color.background_card_fork_false));
        }

        itemView.setOnLongClickListener(view -> showDialog(repository));
    }

    private Boolean showDialog(Repository repository) {
        final Dialog dialog = new Dialog(itemView.getContext());
        dialog.setCancelable(true);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_repo);

        Button ownerButton =  dialog.findViewById(R.id.dialog_owner_button);
        Button repoButton =  dialog.findViewById(R.id.dialog_repo_button);

        ownerButton.setOnClickListener(v -> {
            launchBrowserWithUrl(repository.getOwnerUrl());
            dialog.dismiss();
        });

        repoButton.setOnClickListener(v -> {
            launchBrowserWithUrl(repository.getRepoUrl());
            dialog.dismiss();
        });
        dialog.show();
        return true;
    }

    private void launchBrowserWithUrl(String url) {
        Log.v("dialog", " launch browser with: " + url);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        if (isAvailable(itemView.getContext(), browserIntent)) {
            itemView.getContext().startActivity(browserIntent);
        } else {
            Toast.makeText(itemView.getContext(), itemView.getResources()
                    .getString(R.string.dialog_no_browser), Toast.LENGTH_LONG).show();
        }
    }

    public static class RepoCardHolderFactory extends ViewHolderFactory {

        @Inject
        RepoCardHolderFactory(@NonNull @ForActivity final Context context) {
            super(context);
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder createViewHolder(@NonNull final ViewGroup parent) {
            return new RepositoryViewHolder(LayoutInflater.from(context)
                    .inflate(R.layout.repo_recycler_row, parent, false));
        }
    }

    public static class RepoCardHolderBinder implements ViewHolderBinder {

        @Inject
        RepoCardHolderBinder() { }

        @Override
        public void bind(@NonNull final RecyclerView.ViewHolder viewHolder,
                         @NonNull final DisplayableItem item) {
            RepositoryViewHolder castedViewHolder = RepositoryViewHolder.class.cast(viewHolder);
            Repository viewEntity = Repository.class.cast(item.model());
            castedViewHolder.bind(viewEntity);
        }
    }
}
