package com.goforer.github_clean_architecture_mvp.presentation.dagger.module;

import android.app.Application;
import android.content.Context;

import com.goforer.github_clean_architecture_mvp.presentation.Github_Clean_Architecture;
import com.goforer.github_clean_architecture_mvp.presentation.dagger.annotation.ForApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * A module for Android-specific dependencies which require a {@link Context} or
 * {@link android.app.Application} to create.
 */
@Module
public class AppModule {
    private Github_Clean_Architecture mApplication;

    public AppModule(Github_Clean_Architecture application) {
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
