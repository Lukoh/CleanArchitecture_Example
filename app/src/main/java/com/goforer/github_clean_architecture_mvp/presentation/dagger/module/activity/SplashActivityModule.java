package com.goforer.github_clean_architecture_mvp.presentation.dagger.module.activity;

import com.goforer.base.presentation.view.BaseView;
import com.goforer.github_clean_architecture_mvp.presentation.contract.SplashContract;
import com.goforer.github_clean_architecture_mvp.presentation.dagger.annotation.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * A module for Android-specific dependencies which require a {@link BaseView}
 */
@Module
public class SplashActivityModule {
    private SplashContract.View mView;

    public SplashActivityModule(SplashContract.View view) {
        mView = view;
    }

    @Provides
    @PerActivity
    SplashContract.View providesSplashContractView() {
        return mView;
    }
}
