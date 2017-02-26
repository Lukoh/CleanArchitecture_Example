package com.goforer.base.presentation.view.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;

import com.goforer.base.presentation.model.event.ActivityStackClearEvent;
import com.goforer.base.presentation.utils.ConnectionUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    private boolean mIsResumed = false;
    private boolean mIsNetworkAvailable = false;

    private Activity mCurrentActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventBus.getDefault().register(this);
        setContentView();
        bindViews();
        setActionBar();

        if (ConnectionUtils.INSTANCE.isNetworkAvailable(this)) {
            mIsNetworkAvailable = true;
            setViews(savedInstanceState);
        } else {
            mIsNetworkAvailable = false;
        }
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);

        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();

        mIsResumed = true;
        mCurrentActivity = this;

    }

    @Override
    protected void onPause() {
        super.onPause();

        mIsResumed = false;

    }

    /**
     * Return true if this activity is resumed
     *
     * @return true if this activity is resumed
     */
    @SuppressWarnings("unused")
    public boolean resumed() {
        return mIsResumed;
    }

    /**
     * Initialize the ActionBar and set options into it.
     *
     * @see ActionBar
     */
    protected void setActionBar() {
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_TITLE);
        }
    }

    /**
     * Set the activity content from a layout resource.  The resource will be
     * inflated, adding all top-level views to the activity.
     * <p>
     * All activity must implement this method to get the resource inflated like below example:
     *
     * Example :
     * @@Override
     * public void setContentView() {
     * setContentView(R.layout.activity_gallery);
     * }
     * </p>
     *
     * @see #setContentView(android.view.View, android.view.ViewGroup.LayoutParams)
     */
    protected abstract void setContentView();

    /**
     * Inject annotated fields and methods in the specified target {@link Activity} for field
     * injection. The current content view is used as the view root.
     *
     * @see ButterKnife#bind(Activity target)
     */
    protected void bindViews() {
        ButterKnife.bind(this);
    }

    /**
     * Initialize all views to set into the activity.
     * <p>
     * The activity which has no Fragment must override this method to set all views
     * into the activity.
     * </p>
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    protected void setViews(Bundle savedInstanceState) {
    }

    @Override
    public void finish() {
        super.finish();
    }

    /**
     * Transact an existing fragment that was added to a container.
     *
     * @param cls the component class that is to be used for BaseActivity
     * @param containerViewId Identifier of the container whose fragment(s) are to be replaced.
     * @param args Bundle of arguments to supply to the fragment
     */
    protected Fragment transactFragment(Class<?> cls, @IdRes int containerViewId, Bundle args) {
        return transactFragment(cls.getName(), containerViewId, args);
    }

    /**
     * Transact an existing fragment that was added to a container.
     *
     * @param tag Optional tag name for the fragment
     * @param containerViewId Identifier of the container whose fragment(s) are to be replaced.
     * @param args Bundle of arguments to supply to the fragment
     */
    protected Fragment transactFragment(String tag, @IdRes int containerViewId, Bundle args) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment == null) {
            fragment = Fragment.instantiate(this, tag, args);
        }

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(containerViewId, fragment, tag);
        ft.commit();

        return fragment;
    }

    /**
     * Return previously set Fragment with given the component class.
     *
     * @param cls The previously set the component class that is to be used for BaseActivity.
     *
     * @return The previously set Fragment
     */
    @SuppressWarnings("unused")
    protected Fragment getFragment(Class<?> cls) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        return fragmentManager.findFragmentByTag(cls.getName());
    }

    /**
     * Return previously set Fragment with given the tag.
     *
     * @param tag The previously set the component class tag that is to be used for BaseActivity.
     *
     * @return The previously set Fragment
     */
    @SuppressWarnings("unused")
    protected Fragment getFragment(String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        return fragmentManager.findFragmentByTag(tag);
    }

    /**
     * Check if the network is available
     *
     * @return return true if the network is available
     */
    protected boolean isNetworkAvailable() {
        return mIsNetworkAvailable;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Return currently set Activity
     *
     * @return The currently set Activity
     */
    @SuppressWarnings("unused")
    public Activity getCurrentActivity() {
        return mCurrentActivity;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ActivityStackClearEvent event) {
        finish();
    }

}
