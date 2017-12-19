package com.gve.testapplication.core.injection.module;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gve.testapplication.core.data.AppDataBase;
import com.gve.testapplication.core.data.ReactiveStoreSingular;
import com.gve.testapplication.core.data.roomjsonstore.RoomJsonStore;
import com.gve.testapplication.core.injection.qualifiers.ForApplication;
import com.gve.testapplication.core.injection.qualifiers.Similar;
import com.gve.testapplication.core.injection.qualifiers.TopRated;
import com.gve.testapplication.feature.MovieDetail;
import com.gve.testapplication.feature.moviedetail.data.MovieDetailPagerRepo;
import com.gve.testapplication.feature.moviedetail.data.MovieDetailRepo;
import com.gve.testapplication.feature.movieslist.data.ListMovieRepo;
import com.gve.testapplication.feature.Movie;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by gve.
 */

@Module
public final class DataModule {

    @Provides
    @Singleton
    @TopRated
    ReactiveStoreSingular<List<Movie>> provideRoomMovieListRepoStore(
            @ForApplication Context context,
            Gson gson) {
        return new RoomJsonStore<List<Movie>>(
                AppDataBase.getDatabase(context).roomJsonModel(),
                ListMovieRepo.getKeyFunction(),
                json -> gson.fromJson(json, new TypeToken<List<Movie>>() { }.getType()),
                gson::toJson,
                () -> "[]");
    }

    @Provides
    @Singleton
    @Similar
    ReactiveStoreSingular<List<Movie>> provideRoomMovieListSimilarRepoStore(
            @ForApplication Context context,
            Gson gson) {
        return new RoomJsonStore<List<Movie>>(
                AppDataBase.getDatabase(context).roomJsonModel(),
                MovieDetailPagerRepo.getKeyFunction(),
                json -> gson.fromJson(json, new TypeToken<List<Movie>>() { }.getType()),
                gson::toJson,
                () -> "[]");
    }

    @Provides
    @Singleton
    ReactiveStoreSingular<MovieDetail> provideRoomMovieDetialRepoStore(
            @ForApplication Context context,
            Gson gson) {
        return new RoomJsonStore<MovieDetail>(
                AppDataBase.getDatabase(context).roomJsonModel(),
                MovieDetailRepo.getKeyFunction(),
                json -> gson.fromJson(json, new TypeToken<MovieDetail>() { }.getType()),
                gson::toJson,
                () -> "");
    }



}
