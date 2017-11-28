package com.gve.testapplication;

import android.util.Log;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.gve.testapplication.core.injection.module.NetworkModule;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by gve on 26/10/2017.
 */

// don't work with IntoSet when we inject in Application
@Module
public class InstrumentationModule {

    @Provides
    @Singleton
    static HttpLoggingInterceptor provideLoggingInterceptor() {
        return new HttpLoggingInterceptor(message -> Log.d("OkHttp", message))
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @Singleton
    static StethoInterceptor provideStethoInterceptor() {
        return new StethoInterceptor();
    }

    @Provides
    @Singleton
    @NetworkModule.NetworkInterceptor
    static Set<Interceptor> provideNetworkInterceptors(HttpLoggingInterceptor loggingInterceptor,
                                                       StethoInterceptor stethoInterceptor) {
        Set<Interceptor> networkInterceptors = new HashSet<>(2);
        networkInterceptors.add(loggingInterceptor);
        networkInterceptors.add(stethoInterceptor);
        return networkInterceptors;
    }

}
