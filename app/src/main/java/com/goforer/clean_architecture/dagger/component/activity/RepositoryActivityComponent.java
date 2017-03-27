package com.goforer.clean_architecture.dagger.component.activity;

import com.goforer.clean_architecture.dagger.annotation.scope.PerActivity;
import com.goforer.clean_architecture.dagger.module.AppModule;
import com.goforer.clean_architecture.dagger.module.activity.RepositoryActivityModule;
import com.goforer.clean_architecture.presentation.view.activity.RepositoryActivity;

import dagger.Component;

@PerActivity
@Component(
        dependencies = AppModule.class,
        modules = RepositoryActivityModule.class
)

@SuppressWarnings("WeakerAccess")
public interface RepositoryActivityComponent {
    void inject(RepositoryActivity repositoryActivity);
}
