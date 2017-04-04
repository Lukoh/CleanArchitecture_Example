package com.goforer.clean_architecture.dependencyinjection.component;

import android.content.Context;

import com.goforer.clean_architecture.CleanArchitectureApplication;
import com.goforer.clean_architecture.dependencyinjection.module.AppModule;

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
