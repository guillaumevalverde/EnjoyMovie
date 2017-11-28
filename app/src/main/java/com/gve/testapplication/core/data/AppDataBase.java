package com.gve.testapplication.core.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.gve.testapplication.core.data.roomjsonstore.RoomJson;
import com.gve.testapplication.core.data.roomjsonstore.RoomJsonModelDao;

/**
 * Created by gve on 07/11/2017.
 */

@Database(entities = {RoomJson.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase instance;

    public static AppDataBase getDatabase(Context context) {
        if (instance == null) {
            instance =  Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "App_DataBase")
                            .build();
        }
        return instance;
    }

    public abstract RoomJsonModelDao roomJsonModel();

}
