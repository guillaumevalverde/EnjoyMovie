package com.gve.testapplication.core.injection.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.gve.testapplication.core.injection.qualifiers.ForApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by gve.
 */

@Module
public class BootCampModule {

    @ForApplication
    @Provides
    @Singleton
    Context provideApplicationContext(Application app) {
        return app.getApplicationContext();
    }

    @Provides
    @Singleton
    static SharedPreferences provideSharedPreference(@ForApplication Context context) {
        return context.getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
    }

}
