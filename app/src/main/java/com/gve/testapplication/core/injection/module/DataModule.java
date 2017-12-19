package com.gve.testapplication.core.injection.module;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gve.testapplication.core.data.AppDataBase;
import com.gve.testapplication.core.data.ReactiveStoreSingular;
import com.gve.testapplication.core.data.roomjsonstore.RoomJsonStore;
import com.gve.testapplication.core.injection.qualifiers.ForApplication;
import com.gve.testapplication.core.injection.qualifiers.Similar;
import com.gve.testapplication.core.injection.qualifiers.TopRated;
import com.gve.testapplication.feature.data.MovieDetail;
import com.gve.testapplication.feature.moviedetail.data.MovieDetailPagerRepo;
import com.gve.testapplication.feature.moviedetail.data.MovieDetailRepo;
import com.gve.testapplication.feature.movieslist.data.ListMovieRepo;
import com.gve.testapplication.feature.data.Movie;

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
    @TopRated
    ReactiveStoreSingular<List<Movie>> provideRoomMovieListRepoStore(
            @ForApplication Context context,
            AppDataBase appDataBase,
            Gson gson) {
        return new RoomJsonStore<List<Movie>>(
                appDataBase.roomJsonModel(),
                ListMovieRepo.getKeyFunction(),
                json -> gson.fromJson(json, new TypeToken<List<Movie>>() { }.getType()),
                gson::toJson,
                () -> "[]");
    }

    @Provides
    @Similar
    ReactiveStoreSingular<List<Movie>> provideRoomMovieListSimilarRepoStore(
            @ForApplication Context context,
            AppDataBase appDataBase,
            Gson gson) {
        return new RoomJsonStore<List<Movie>>(
                appDataBase.roomJsonModel(),
                MovieDetailPagerRepo.getKeyFunction(),
                json -> gson.fromJson(json, new TypeToken<List<Movie>>() { }.getType()),
                gson::toJson,
                () -> "[]");
    }

    @Provides
    ReactiveStoreSingular<MovieDetail> provideRoomMovieDetialRepoStore(
            @ForApplication Context context,
            AppDataBase appDataBase,
            Gson gson) {
        return new RoomJsonStore<MovieDetail>(
                appDataBase.roomJsonModel(),
                MovieDetailRepo.getKeyFunction(),
                json -> gson.fromJson(json, new TypeToken<MovieDetail>() { }.getType()),
                gson::toJson,
                () -> "");
    }

    @Provides
    @Singleton
    AppDataBase provideAppDataBase(@ForApplication Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "App_DataBase")
                .build();
    }


}
