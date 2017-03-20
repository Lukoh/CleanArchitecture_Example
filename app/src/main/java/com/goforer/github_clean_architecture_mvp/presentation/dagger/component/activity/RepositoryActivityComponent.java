package com.goforer.github_clean_architecture_mvp.presentation.dagger.component.activity;

import com.goforer.github_clean_architecture_mvp.presentation.dagger.annotation.scope.PerActivity;
import com.goforer.github_clean_architecture_mvp.presentation.dagger.module.AppModule;
import com.goforer.github_clean_architecture_mvp.presentation.dagger.module.activity.RepositoryActivityModule;
import com.goforer.github_clean_architecture_mvp.presentation.view.activity.RepositoryActivity;

import dagger.Component;

@PerActivity
@Component(
        dependencies = AppModule.class,
        modules = RepositoryActivityModule.class
)
public interface RepositoryActivityComponent {
    void inject(RepositoryActivity repositoryActivity);
}
