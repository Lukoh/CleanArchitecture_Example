package com.goforer.clean_architecture.dagger.module.activity;

import android.content.Context;

import com.goforer.base.presentation.view.BaseView;
import com.goforer.clean_architecture.presentation.contract.RepositoryContract;
import com.goforer.clean_architecture.dagger.annotation.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * A module for Android-specific dependencies which require a {@link BaseView}
 */
@Module
public class RepositoryActivityModule {
    @SuppressWarnings("unused")
    private final Context mContext;

    private final RepositoryContract.View mView;

    public RepositoryActivityModule(Context context, RepositoryContract.View view) {
        mContext = context;
        mView = view;
    }

    @Provides
    @PerActivity
    RepositoryContract.View providesRepositoryContractView() {
        return mView;
    }

}
