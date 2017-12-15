package com.gve.testapplication.core.injection.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.gve.testapplication.core.preconditions.Preconditions;

/**
 * Created by gve on 04/12/2017.
 */

public abstract class BaseInjectingActivity<Component> extends BaseActivity {

    @Nullable
    private Component component;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        component = createComponent();
        onInject(component);

        super.onCreate(savedInstanceState);
    }

    @NonNull
    public Component getComponent() {
        return Preconditions.get(component);
    }

    protected abstract void onInject(@NonNull Component component);

    @NonNull
    protected abstract Component createComponent();
}
