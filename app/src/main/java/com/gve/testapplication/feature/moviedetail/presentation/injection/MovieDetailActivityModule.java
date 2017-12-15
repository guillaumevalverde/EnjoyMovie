package com.gve.testapplication.feature.moviedetail.presentation.injection;

import android.content.Context;

import com.gve.testapplication.core.injection.qualifiers.ForActivity;
import com.gve.testapplication.core.injection.scopes.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by gve on 15/12/2017.
 */

@Module
public class MovieDetailActivityModule {

    @ForActivity
    private Context context;

    public MovieDetailActivityModule(Context context) {
        this.context = context;
    }

    @Provides
    @ActivityScope
    @ForActivity
    public Context getContext() {
        return context;
    }
}
