package com.goforer.github_clean_architecture_mvp.presentation.dagger.component;

import android.content.Context;

import com.goforer.github_clean_architecture_mvp.presentation.Github_Clean_Architecture;
import com.goforer.github_clean_architecture_mvp.presentation.dagger.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = AppModule.class
)
public interface AppComponent {
    @SuppressWarnings("unused")
    Github_Clean_Architecture getApplication(Github_Clean_Architecture application);

    @SuppressWarnings("unused")
    Context getApplicationContext(Context context);
}
