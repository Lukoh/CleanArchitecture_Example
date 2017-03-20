package com.goforer.github_clean_architecture_mvp.presentation.dagger.component.fragment;

import com.goforer.github_clean_architecture_mvp.presentation.dagger.annotation.scope.PerFragment;
import com.goforer.github_clean_architecture_mvp.presentation.dagger.module.AppModule;
import com.goforer.github_clean_architecture_mvp.presentation.dagger.module.fragment.RepositoryFragmentModule;
import com.goforer.github_clean_architecture_mvp.presentation.view.fragment.RepositoryFragment;

import dagger.Component;

@PerFragment
@Component(
        dependencies = AppModule.class,
        modules = RepositoryFragmentModule.class
)
public interface RepositoryFragmentComponent {
    void inject(RepositoryFragment repositoryFragment);
}