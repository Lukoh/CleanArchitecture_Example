package com.goforer.github_clean_architecture_mvp.presentation.dagger.component.activity;

import com.goforer.github_clean_architecture_mvp.presentation.dagger.annotation.scope.PerActivity;
import com.goforer.github_clean_architecture_mvp.presentation.dagger.module.AppModule;
import com.goforer.github_clean_architecture_mvp.presentation.dagger.module.activity.SplashActivityModule;
import com.goforer.github_clean_architecture_mvp.presentation.view.activity.SplashActivity;

import dagger.Component;

@PerActivity
@Component(
        dependencies = AppModule.class,
        modules = SplashActivityModule.class
)
public interface SplashActivityComponent {
     void inject(SplashActivity splashActivity);
}
