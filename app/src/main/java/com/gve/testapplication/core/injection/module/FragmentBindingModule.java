package com.gve.testapplication.core.injection.module;

import com.gve.testapplication.core.injection.SubcomponentBuilder;
import com.gve.testapplication.core.injection.SubcomponentKey;
import com.gve.testapplication.feature.moviedetail.presentation.injection.MovieDetailFragmentComponent;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by gve.
 */

@Module(subcomponents = {MovieDetailFragmentComponent.class})

public abstract class FragmentBindingModule {

    @Binds @IntoMap
    @SubcomponentKey(MovieDetailFragmentComponent.Builder.class)
    public abstract SubcomponentBuilder movieDetialFragment(MovieDetailFragmentComponent.Builder impl);

}
