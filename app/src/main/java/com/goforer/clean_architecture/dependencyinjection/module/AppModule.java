package com.goforer.clean_architecture.dependencyinjection.module;

import android.app.Application;
import android.content.Context;

import com.goforer.clean_architecture.CleanArchitectureApplication;
import com.goforer.clean_architecture.dependencyinjection.annotation.ForApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * A module for Android-specific dependencies which require a {@link Context} or
 * {@link android.app.Application} to create.
 */
@Module
public class AppModule {
    private CleanArchitectureApplication mApplication;

    public AppModule(CleanArchitectureApplication application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    @ForApplication
    public Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    @ForApplication
    public Context provideApplicationContext() {
        return mApplication;
    }
}
