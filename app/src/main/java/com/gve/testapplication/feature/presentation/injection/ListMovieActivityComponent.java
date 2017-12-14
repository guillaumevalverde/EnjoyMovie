package com.gve.testapplication.feature.presentation.injection;
import com.gve.testapplication.core.injection.SubcomponentBuilder;
import com.gve.testapplication.core.injection.scopes.ActivityScope;
import com.gve.testapplication.feature.presentation.ListMovieActivity;

import dagger.Subcomponent;

/**
 * Created by gve on 26/10/2017.
 */

@ActivityScope
@Subcomponent(modules = {ListMovieActivityModule.class, RecyclerViewMovieListModule.class})
public interface ListMovieActivityComponent {

    @Subcomponent.Builder
    interface Builder extends SubcomponentBuilder<ListMovieActivityComponent> {
        Builder activityModule(ListMovieActivityModule module);
    }

    void inject(ListMovieActivity activity);
}
