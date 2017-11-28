package com.gve.testapplication.ListOfRepoFeature.presentation.injection;
import com.gve.testapplication.ListOfRepoFeature.presentation.ListRepositoryActivity;
import com.gve.testapplication.core.injection.SubcomponentBuilder;
import com.gve.testapplication.core.injection.scopes.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by gve on 26/10/2017.
 */

@ActivityScope
@Subcomponent(modules = {ListRepoActivityModule.class, RecyclerViewRepoActivityModule.class})
public interface ListRepoActivityComponent {

    @Subcomponent.Builder
    interface Builder extends SubcomponentBuilder<ListRepoActivityComponent> {
        Builder activityModule(ListRepoActivityModule module);
    }

    void inject(ListRepositoryActivity activity);
}
