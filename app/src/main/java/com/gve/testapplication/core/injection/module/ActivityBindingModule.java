package com.gve.testapplication.core.injection.module;

import com.gve.testapplication.ListOfRepoFeature.presentation.injection.ListRepoActivityComponent;
import com.gve.testapplication.core.injection.SubcomponentKey;
import com.gve.testapplication.core.injection.SubcomponentBuilder;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by gve.
 */

@Module(subcomponents = {
        ListRepoActivityComponent.class
})

public abstract class ActivityBindingModule {

    @Binds @IntoMap
    @SubcomponentKey(ListRepoActivityComponent.Builder.class)
    public abstract SubcomponentBuilder listRepoActivity(ListRepoActivityComponent.Builder impl);

}
