package com.goforer.github_clean_architecture_mvp.presentation.dagger.module.logic;

import com.goforer.github_clean_architecture_mvp.domain.sort.SortImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SortModule {
    @Provides
    @Singleton
    SortImpl providesSort() {
        return new SortImpl<>();
    }
}
