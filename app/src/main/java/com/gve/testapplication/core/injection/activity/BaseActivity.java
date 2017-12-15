package com.gve.testapplication.core.injection.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by gve on 04/12/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        setContentView(getLayoutId());
        super.onCreate(savedInstanceState);
    }

    @LayoutRes
    protected abstract int getLayoutId();

}
