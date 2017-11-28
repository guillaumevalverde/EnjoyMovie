package com.gve.testapplication;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.util.Pair;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.gve.testapplication.ListOfRepoFeature.data.ListRepositoryRepo;
import com.gve.testapplication.ListOfRepoFeature.data.Repository;
import com.gve.testapplication.core.data.AppDataBase;
import com.gve.testapplication.core.data.roomjsonstore.RoomJson;
import com.gve.testapplication.core.data.roomjsonstore.RoomJsonStore;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import io.reactivex.functions.Predicate;
import io.reactivex.observers.TestObserver;
import io.reactivex.subscribers.TestSubscriber;

@RunWith(AndroidJUnit4.class)
public class DataRoomTest {

    private AppDataBase appDataBase;
    private Gson gson;

    @Before
    public void initDb() {
        appDataBase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                AppDataBase.class).build();
        final GsonBuilder builder = new GsonBuilder();
        gson = builder.create();

    }

    @After
    public void closeDb() {
        appDataBase.close();
    }

    @Test
    public void insertSavesData() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        List<Repository> list = PojoUtils.getList(gson).blockingGet();

        RoomJson dataToBeSaved = new RoomJson("data1", 10000, gson.toJson(list));

        appDataBase.roomJsonModel().add(dataToBeSaved);

        TestObserver<RoomJson> testObserver = appDataBase.roomJsonModel().getItembyIdSingle("data1").test();

        testObserver.assertComplete();
    }

    @Test
    public void getInitDataFromStoreIfDataBaseEmpty() throws Exception {
        RoomJsonStore store = new RoomJsonStore<List<Repository>>(
                appDataBase,
                ListRepositoryRepo.getKeyFunction(),
                json -> gson.fromJson(json, new TypeToken<List<Repository>>(){ }.getType()),
                gson::toJson,
                () -> "[]");

        TestSubscriber<Pair<Long, List<Repository>>> testSubscriber = store.getSingularStream("key").test();

        testSubscriber.assertNoErrors();
        testSubscriber.assertValueAt(0, new Predicate<Pair<Long, List<Repository>>>() {
            @Override
            public boolean test(Pair<Long, List<Repository>> pair) throws Exception {
                return pair.second.isEmpty();
            }
        });
    }
}
