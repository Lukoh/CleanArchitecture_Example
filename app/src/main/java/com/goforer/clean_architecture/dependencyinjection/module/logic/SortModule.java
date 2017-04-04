package com.goforer.clean_architecture.dependencyinjection.module.logic;

import com.goforer.clean_architecture.domain.sort.SortImpl;

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
