package com.goforer.github_clean_architecture_mvp.presentation.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.goforer.base.presentation.view.activity.BaseActivity;
import com.goforer.github_clean_architecture_mvp.R;
import com.goforer.github_clean_architecture_mvp.presentation.caller.Caller;
import com.goforer.github_clean_architecture_mvp.presentation.model.data.User;
import com.goforer.github_clean_architecture_mvp.presentation.presenter.RepositoryPresenter;
import com.goforer.github_clean_architecture_mvp.presentation.view.fragment.RepositoryFragment;

import butterknife.BindView;

public class RepositoryActivity extends BaseActivity {
    private Fragment mFragment;
    /**
     * Notice
     *
     * In case of being presenter object used in Activity, the presenter object have to be declared here.
     * But if not, it have to be declared as local variable not class member variable.
     */
    private RepositoryPresenter mPresenter;

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

        // Create the presenter
        mPresenter
                = new RepositoryPresenter((RepositoryFragment)mFragment);
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
    protected void setViews(Bundle savedInstanceState) {
        mFragment = transactFragment(RepositoryFragment.class, R.id.content_holder, null);
    }

    @Override
    protected void setActionBar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_USE_LOGO);
            actionBar.setTitle(mUserProfile.getName() + "'s " + getString(R.string.repository));
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
    public void onBackPressed() {
        super.onBackPressed();
    }

    public User getUserProfile() {
        return mUserProfile;
    }
}
