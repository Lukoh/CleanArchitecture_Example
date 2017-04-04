package com.goforer.clean_architecture.dependencyinjection.component.logic;

import com.goforer.clean_architecture.domain.sort.SortImpl;
import com.goforer.clean_architecture.dependencyinjection.module.logic.SortModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = SortModule.class
)

@SuppressWarnings("WeakerAccess")
public interface SortComponent {
    // downstream components need these exposed with the return type
    // method name does not really matter
    SortImpl getInstance();
}
