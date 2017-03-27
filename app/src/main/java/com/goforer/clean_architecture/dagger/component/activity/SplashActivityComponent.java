package com.goforer.clean_architecture.dagger.component.activity;

import com.goforer.clean_architecture.dagger.annotation.scope.PerActivity;
import com.goforer.clean_architecture.dagger.module.AppModule;
import com.goforer.clean_architecture.dagger.module.activity.SplashActivityModule;
import com.goforer.clean_architecture.presentation.view.activity.SplashActivity;

import dagger.Component;

@PerActivity
@Component(
        dependencies = AppModule.class,
        modules = SplashActivityModule.class
)

@SuppressWarnings("WeakerAccess")
public interface SplashActivityComponent {
     void inject(SplashActivity splashActivity);
}
