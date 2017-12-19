package com.gve.testapplication.core.data.roomjsonstore

import android.arch.persistence.room.EmptyResultSetException
import android.support.v4.util.Pair
import com.gve.testapplication.core.data.AppDataBase
import com.gve.testapplication.core.data.ReactiveStoreSingular

import java.util.Date
import java.util.concurrent.Callable

import javax.inject.Inject

import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function

/**
 * Created by gve on 15/11/2017.
 */

class RoomJsonStore<Value> @Inject
constructor(private val roomJsonModel: RoomJsonModelDao,
            private var getKey: BiFunction<Value, Long, String>,
            private val getObjFromJson: Function<String, Value>,
            private val getJsonFromObj: Function<Value, String>,
            private val getEmptyValue: Callable<String>) : ReactiveStoreSingular<Value> {

    @Throws(Exception::class)
    override fun storeSingular(model: Value, key: Long) {
        val roomJson = RoomJson(getKey.apply(model, key), time.call(), getJsonFromObj.apply(model))
        roomJsonModel.add(roomJson)
    }

    override fun getSingularStream(key: String): Flowable<Pair<Long, Value>> {

        val first = roomJsonModel.getItembyIdSingle(key).toFlowable()
                .onErrorReturn { _ -> RoomJson.getEmptyRoom(getEmptyValue) }

        val second = roomJsonModel.getItembyId(key)

        return Flowable.concat(first, second)
                .map { roomJson -> Pair(roomJson.timeStamp, getObjFromJson.apply(roomJson.json)) }
    }

    override fun getSingularSingle(key: String): Single<Pair<Long, Value>> {
        return roomJsonModel.getItembyIdSingle(key)
                .map { roomJson -> Pair(roomJson.timeStamp, getObjFromJson.apply(roomJson.json)) }
    }

    companion object {

        val time: Callable<Long>
            get() {
                val date = Date()
                return Callable { date.time }
            }
    }

}
