package com.goforer.clean_architecture.dagger.component.fragment;

import com.goforer.clean_architecture.dagger.annotation.scope.PerFragment;
import com.goforer.clean_architecture.dagger.module.AppModule;
import com.goforer.clean_architecture.dagger.module.fragment.RepositoryFragmentModule;
import com.goforer.clean_architecture.presentation.view.fragment.RepositoryFragment;

import dagger.Component;

@PerFragment
@Component(
        dependencies = AppModule.class,
        modules = RepositoryFragmentModule.class
)

@SuppressWarnings("WeakerAccess")
public interface RepositoryFragmentComponent {
    void inject(RepositoryFragment repositoryFragment);
}