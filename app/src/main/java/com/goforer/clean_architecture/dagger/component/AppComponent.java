package com.goforer.clean_architecture.dagger.component;

import android.content.Context;

import com.goforer.clean_architecture.presentation.CleanArchitectureApplication;
import com.goforer.clean_architecture.dagger.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = AppModule.class
)

@SuppressWarnings("WeakerAccess")
public interface AppComponent {
    @SuppressWarnings("unused")
    CleanArchitectureApplication getApplication(CleanArchitectureApplication application);

    @SuppressWarnings("unused")
    Context getApplicationContext(Context context);
}
