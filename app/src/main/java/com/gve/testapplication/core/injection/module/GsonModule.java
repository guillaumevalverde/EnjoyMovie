package com.gve.testapplication.core.injection.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;

import java.util.Set;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;

/**
 * Created by gve.
 */

@Module
public class GsonModule {

    @Provides
    @Singleton
    static Gson provideGson() {
        final GsonBuilder builder = new GsonBuilder();
        return builder.create();
    }

}
