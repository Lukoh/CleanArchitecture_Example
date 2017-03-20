package com.goforer.github_clean_architecture_mvp.presentation.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.goforer.base.presentation.utils.CommonUtils;
import com.goforer.base.presentation.view.activity.BaseActivity;
import com.goforer.github_clean_architecture_mvp.R;
import com.goforer.github_clean_architecture_mvp.presentation.Github_Clean_Architecture;
import com.goforer.github_clean_architecture_mvp.presentation.caller.Caller;
import com.goforer.github_clean_architecture_mvp.presentation.contract.SplashContract;
import com.goforer.github_clean_architecture_mvp.presentation.dagger.component.activity.DaggerSplashActivityComponent;
import com.goforer.github_clean_architecture_mvp.presentation.dagger.module.AppModule;
import com.goforer.github_clean_architecture_mvp.presentation.dagger.module.activity.SplashActivityModule;
import com.goforer.github_clean_architecture_mvp.presentation.model.data.User;
import com.goforer.github_clean_architecture_mvp.presentation.presenter.SplashPresenter;

import javax.inject.Inject;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class SplashActivity extends BaseActivity implements SplashContract.View {
    private static final int MIN_SPLASH_TIME = 4400;
    public static final String USER_NAME = "jakewharton";

    @Inject
    SplashPresenter mPresenter;

    private long mSplashStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!isNetworkAvailable()) {
            CommonUtils.showToastMessage(this, getString(R.string.not_connected_internet),
                    Toast.LENGTH_SHORT);
            finish();
        }

        mSplashStart = System.currentTimeMillis();
        mPresenter.getUser(USER_NAME);
    }

    @Override
    protected void setupActivityComponent() {
        DaggerSplashActivityComponent.builder()
                .appModule(new AppModule((Github_Clean_Architecture) getApplication()))
                .splashActivityModule(new SplashActivityModule(this)).build().inject(this);
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mPresenter.start();
        onWait();
    }

    @Override
    protected void onStop() {
        mPresenter.stop();

        super.onStop();
    }

    @Override
    public void setPresenter(@NonNull SplashContract.Presenter presenter) {
        mPresenter = (SplashPresenter) checkNotNull(presenter);
    }

    @Override
    public void setUserProfile(User user) {
        moveToMain(user);
    }

    @Override
    public void showError(Context context, String errorMessage) {
        CommonUtils.showToastMessage(this, errorMessage, Toast.LENGTH_SHORT);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Github_Clean_Architecture.closeApplication();
            }
        }, Toast.LENGTH_SHORT);
    }

    private void onWait() {
        long elapsed = System.currentTimeMillis() - mSplashStart;
        long more_splash = MIN_SPLASH_TIME <= elapsed ? 0 : MIN_SPLASH_TIME - elapsed;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, more_splash);
    }

    public void moveToMain(User userProfile) {
        Caller.INSTANCE.callRepository(this, userProfile);
        finish();
    }
}
