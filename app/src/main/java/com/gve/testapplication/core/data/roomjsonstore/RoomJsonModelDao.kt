package com.gve.testapplication.core.data.roomjsonstore

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

import io.reactivex.Flowable
import io.reactivex.Single

import android.arch.persistence.room.OnConflictStrategy.REPLACE

/**
 * Created by gve on 15/11/2017.
 */

@Dao
interface RoomJsonModelDao {

    @Query("select * from roomJsonTable where id = :id")
    fun getItembyId(id: String): Flowable<RoomJson>

    @Insert(onConflict = REPLACE)
    fun add(json: RoomJson)

    @Query("delete from roomJsonTable where id = :key")
    fun delete(key: String)

    @Query("select * from roomJsonTable where id = :id")
    fun getItembyIdSingle(id: String): Single<RoomJson>

}
