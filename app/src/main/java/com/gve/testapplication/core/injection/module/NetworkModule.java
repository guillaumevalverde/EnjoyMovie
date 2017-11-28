package com.gve.testapplication.core.injection.module;

import com.google.gson.Gson;
import com.gve.testapplication.InstrumentationModule;
import com.gve.testapplication.ListOfRepoFeature.data.GitHubApiService;
import com.gve.testapplication.core.AppConstUtils;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Set;

import javax.inject.Named;
import javax.inject.Qualifier;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = {GsonModule.class, InstrumentationModule.class})
public final class NetworkModule {

    private static final String API_GITHUB_URL = "API_GITHUB_URL";

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface NetworkInterceptor {
    }

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Github {
    }

    @Provides
    @Singleton
    @Github
    static Retrofit provideGitHubApi(@Named(API_GITHUB_URL) String baseUrl, Gson gson,
                                     OkHttpClient client) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .baseUrl(baseUrl)
                .build();
    }

    @Provides
    @Named(API_GITHUB_URL)
    static String provideGitHubUrl() {
        return AppConstUtils.GITHUB_API_URL;
    }

    @Provides
    @Singleton
    static OkHttpClient provideApiOkHttpClient(@NetworkInterceptor Set<Interceptor>
                                                       networkInterceptor) {
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
        okBuilder.networkInterceptors().addAll(networkInterceptor);

        return okBuilder.build();
    }

    @Provides
    @Singleton
    static GitHubApiService provideGithubApiService(@Github Retrofit retrofit) {
        return retrofit.create(GitHubApiService.class);
    }
}
