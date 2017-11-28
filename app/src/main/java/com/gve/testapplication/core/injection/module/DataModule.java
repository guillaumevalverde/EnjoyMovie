package com.gve.testapplication.core.injection.module;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gve.testapplication.ListOfRepoFeature.data.ListRepositoryRepo;
import com.gve.testapplication.ListOfRepoFeature.data.Repository;
import com.gve.testapplication.core.data.AppDataBase;
import com.gve.testapplication.core.data.ReactiveStoreSingular;
import com.gve.testapplication.core.data.roomjsonstore.RoomJsonStore;
import com.gve.testapplication.core.injection.qualifiers.ForApplication;

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
    ReactiveStoreSingular<List<Repository>> provideRoomListRepoStore(
            @ForApplication Context context,
            Gson gson) {
        return new RoomJsonStore<List<Repository>>(
                AppDataBase.getDatabase(context).roomJsonModel(),
                ListRepositoryRepo.getKeyFunction(),
                json -> gson.fromJson(json, new TypeToken<List<Repository>>() { }.getType()),
                gson::toJson,
                () -> "[]");
    }

}
