package com.goforer.clean_architecture.presentation.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.goforer.base.presentation.view.activity.BaseActivity;
import com.goforer.clean_architecture.R;
import com.goforer.clean_architecture.CleanArchitectureApplication;
import com.goforer.clean_architecture.presentation.caller.Caller;
import com.goforer.clean_architecture.presentation.contract.RepositoryContract;
import com.goforer.clean_architecture.dependencyinjection.component.activity.DaggerRepositoryActivityComponent;
import com.goforer.clean_architecture.dependencyinjection.module.AppModule;
import com.goforer.clean_architecture.dependencyinjection.module.activity.RepositoryActivityModule;
import com.goforer.clean_architecture.presentation.model.data.User;
import com.goforer.clean_architecture.presentation.presenter.RepositoryPresenter;
import com.goforer.clean_architecture.presentation.view.fragment.RepositoryFragment;

import javax.inject.Inject;

import butterknife.BindView;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class RepositoryActivity extends BaseActivity implements RepositoryContract.View {
    /**
     * Notice
     *
     * In case of being presenter object used in Activity, the presenter object have to be declared here.
     * But if not, it have to be declared as local variable not class member variable.
     */
    @Inject
    RepositoryPresenter mPresenter;

    private User mUserProfile;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.iv_disconnect)
    ImageView mDisconnectImage;
    @BindView(R.id.tv_notice1)
    TextView mNoticeText1;
    @BindView(R.id.tv_notice2)
    TextView mNoticeText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mUserProfile = getIntent().getExtras().getParcelable(Caller.EXTRA_PROFILE);

        super.onCreate(savedInstanceState);

        if (!isNetworkAvailable()) {
            mDisconnectImage.setVisibility(View.VISIBLE);
            mNoticeText1.setVisibility(View.VISIBLE);
            mNoticeText2.setVisibility(View.VISIBLE);

            return;
        }

        mPresenter.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();

        Caller.INSTANCE.unBindService(this);
    }

    @Override
    protected void setupActivityComponent() {
        DaggerRepositoryActivityComponent.builder().appModule(
                new AppModule((CleanArchitectureApplication) getApplication())).repositoryActivityModule(
                new RepositoryActivityModule(getApplicationContext(), this)).build().inject(this);
    }

    @Override
    protected void setViews(Bundle savedInstanceState) {
        transactFragment(RepositoryFragment.class, R.id.content_holder, null);
    }

    @Override
    protected void setActionBar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_USE_LOGO);
            actionBar.setTitle(mUserProfile.name() + "'s " + getString(R.string.repository));
            actionBar.setElevation(1);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_base);
    }

    @Override
    public void setPresenter(@NonNull RepositoryContract.Presenter presenter) {
        mPresenter = (RepositoryPresenter) checkNotNull(presenter);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public User getUserProfile() {
        return mUserProfile;
    }
}
