package com.goforer.clean_architecture.dagger.module.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.goforer.base.presentation.view.BaseView;
import com.goforer.base.presentation.view.decoration.RemoverItemDecoration;
import com.goforer.base.presentation.view.helper.RecyclerItemTouchHelperCallback;
import com.goforer.clean_architecture.presentation.contract.RepositoryContract;
import com.goforer.clean_architecture.dagger.annotation.scope.PerFragment;
import com.goforer.clean_architecture.presentation.view.adatper.RepositoryAdapter;
import com.goforer.clean_architecture.presentation.view.fragment.RepositoryFragment;
import com.goforer.clean_architecture.presentation.view.view.drawer.SlidingDrawer;

import dagger.Module;
import dagger.Provides;

/**
 * A module for Android-specific dependencies which require a {@link BaseView}
 */
@Module
public class RepositoryFragmentModule {
    private final Context mContext;

    private RepositoryFragment mFragment;

    private final RepositoryContract.View mView;

    public RepositoryFragmentModule(Context context, RepositoryContract.View view) {
        mContext = context;
        mFragment = (RepositoryFragment) view;
        mView = view;
    }

    @Provides
    @PerFragment
    RepositoryContract.View providesRepositoryContractView() {
        return mView;
    }

    @Provides
    @PerFragment
    RepositoryAdapter providesRepositoryAdapter() {
        return new RepositoryAdapter(mContext, mFragment);
    }

    @Provides
    @PerFragment
    Bundle providesBundle() {
        return new Bundle();
    }

    /**
     * If you would like to input some parameters in the module-method, you've to create
     * the object of Parameter's class like above.
     * For example, if there are two parameters in the module-method, you've to create two objects
     * of Parameter's class like below:
     *
     * First parameter : Bundle bundle
     * Second parameter : OkHttpClient okHttpClient
     *
     * @Provides
     * @Singleton
     * Gson provideGson() {
     *     return new GsonBuilder()
     *             // All timestamps are returned in ISO 8601 format:
     *            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
     *             // Blank fields are included as null instead of being omitted.
     *            .serializeNulls()
     *            .create();
     * }
     *
     *
     * @Provides
     * @Singleton
     * OkHttpClient provideOkHttpClient() {
     *  ...
     * }
     *
     *
     * @Provides
     * @Singleton
     * public Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
     *     return new Retrofit.Builder()
     *                 .baseUrl(DribbleApi.END_POINT)
     *                 .addConverterFactory(GsonConverterFactory.create(gson))
     *                 .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
     *                 .client(okHttpClient)
     *                 .build();
     * }
     *
     *
     */
    @Provides
    @PerFragment
    SlidingDrawer providesSlidingDrawer(Bundle bundle) {
        return new SlidingDrawer(mContext, bundle);
    }

    @Provides
    @PerFragment
    int providesInt() {
        return Color.BLUE;
    }

    @Provides
    @PerFragment
    RecyclerItemTouchHelperCallback providesRecyclerItemTouchHelperCallback(
            RepositoryAdapter adapter, int bgColor) {
        return new RecyclerItemTouchHelperCallback(mContext, adapter, bgColor);
    }

    @Provides
    @PerFragment
    RemoverItemDecoration providesRemoverItemDecoration(int bgColor) {
        return new RemoverItemDecoration(bgColor);
    }

    @Provides
    @PerFragment
    LinearLayoutManager providesLinearLayoutManager() {
        return new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
    }

    @Provides
    @PerFragment
    View providesView() {
        return new View(mContext);
    }
}
