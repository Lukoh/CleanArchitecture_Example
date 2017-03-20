package com.goforer.github_clean_architecture_mvp.presentation.dagger.component.logic;

import com.goforer.github_clean_architecture_mvp.domain.sort.SortImpl;
import com.goforer.github_clean_architecture_mvp.presentation.dagger.module.logic.SortModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = SortModule.class
)
public interface SortComponent {
    // downstream components need these exposed with the return type
    // method name does not really matter
    SortImpl sort();
}
