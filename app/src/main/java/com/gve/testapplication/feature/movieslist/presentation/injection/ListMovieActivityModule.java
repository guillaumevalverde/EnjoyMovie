package com.gve.testapplication.feature.movieslist.presentation.injection;

import android.app.Activity;
import android.content.Context;

import com.gve.testapplication.core.injection.qualifiers.ForActivity;
import com.gve.testapplication.core.injection.scopes.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by gve on 26/10/2017.
 */

@Module
public class ListMovieActivityModule {

    @ForActivity
    private Activity activity;

    public ListMovieActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    @ForActivity
    public Context getContext() {
        return activity.getBaseContext();
    }

    @Provides
    @ActivityScope
    @ForActivity
    public Activity getActivity() {
        return activity;
    }
}
