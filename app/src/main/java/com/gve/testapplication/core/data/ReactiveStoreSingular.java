package com.gve.testapplication.core.data;

import android.support.annotation.NonNull;
import android.support.v4.util.Pair;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public interface ReactiveStoreSingular<Value> {

    void storeSingular(@NonNull Value model, long key) throws Exception;

    Flowable<Pair<Long, Value>> getSingularStream(@NonNull String key);

    Single<Pair<Long, Value>> getSingularSingle(@NonNull String key);
}
