package com.goforer.clean_architecture.presentation.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.goforer.base.presentation.utils.CommonUtils;
import com.goforer.base.presentation.view.decoration.RemoverItemDecoration;
import com.goforer.base.presentation.view.fragment.RecyclerFragment;
import com.goforer.base.presentation.view.helper.RecyclerItemTouchHelperCallback;
import com.goforer.clean_architecture.R;
import com.goforer.clean_architecture.presentation.CleanArchitectureApplication;
import com.goforer.clean_architecture.presentation.contract.RepositoryContract;
import com.goforer.clean_architecture.dagger.component.fragment.DaggerRepositoryFragmentComponent;
import com.goforer.clean_architecture.dagger.module.AppModule;
import com.goforer.clean_architecture.dagger.module.fragment.RepositoryFragmentModule;
import com.goforer.clean_architecture.presentation.model.data.Repository;
import com.goforer.clean_architecture.presentation.presenter.RepositoryPresenter;
import com.goforer.clean_architecture.presentation.view.activity.RepositoryActivity;
import com.goforer.clean_architecture.presentation.view.activity.SplashActivity;
import com.goforer.clean_architecture.presentation.view.adatper.RepositoryAdapter;
import com.goforer.clean_architecture.presentation.view.view.drawer.SlidingDrawer;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class RepositoryFragment extends RecyclerFragment<Repository>
        implements RepositoryContract.View {
    private static final String TAG = "RepositoryFragment";

    @Inject
    RepositoryAdapter mAdapter;

    @Inject
    SlidingDrawer mSlidingDrawer;

    @Inject
    RepositoryPresenter mPresenter;

    @Inject
    RecyclerItemTouchHelperCallback mItemTouchHelperCallback;

    @Inject
    RemoverItemDecoration mRemoverItemDecoration;

    @Inject
    LinearLayoutManager mLinearLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_respository_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setItemHasFixedSize(true);
        refresh(true);
        mSlidingDrawer.setRootViewRes(R.id.drawer_container);
        mSlidingDrawer.setType(SlidingDrawer.DRAWER_PROFILE_TYPE);
        mSlidingDrawer.setDrawerInfo(((RepositoryActivity)getActivity()).getUserProfile());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = mSlidingDrawer.getDrawer().saveInstanceState(outState);
        outState = mSlidingDrawer.getDrawerHeader().saveInstanceState(outState);

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();

        mSlidingDrawer.setDrawerInfo(((RepositoryActivity)getActivity()).getUserProfile());
    }

    @Override
    public void setPresenter(@NonNull RepositoryContract.Presenter presenter) {
        mPresenter = (RepositoryPresenter) checkNotNull(presenter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        super.setOnProcessListener(new RecyclerFragment.OnProcessListener() {
            @Override
            public void onScrolledToLast(RecyclerView recyclerView, int dx, int dy) {
                Log.i(TAG, "onScrolledToLast");
            }

            @Override
            public void onScrolling() {
                Log.i(TAG, "onScrolling");
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                Log.i(TAG, "onScrolled");

            }

            @Override
            public void onError(String message) {
                CommonUtils.showToastMessage(mContext, message, Toast.LENGTH_SHORT);
            }
        });

        return mLinearLayoutManager;
    }

    @Override
    protected RecyclerView.ItemDecoration createItemDecoration() {
        return mRemoverItemDecoration;
    }

    @Override
    protected RecyclerView.Adapter createAdapter() {
        mAdapter.setEnableLoadingImage(true);
        getRecyclerView().setAdapter(mAdapter);

        return mAdapter;
    }

    @Override
    protected ItemTouchHelper.Callback createItemTouchHelper() {
        return mItemTouchHelperCallback;
    }

    @Override
    protected boolean isItemDecorationVisible() {
        return true;
    }

    @Override
    protected void requestData(boolean isNew) {
        try {
            requestRepositoryList(isNew);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        Log.i(TAG, "requestData");
    }

    @Override
    protected void setupFragmentComponent() {
        DaggerRepositoryFragmentComponent.builder()
                .appModule(new AppModule((CleanArchitectureApplication) getActivity().getApplication()))
                .repositoryFragmentModule(new RepositoryFragmentModule(getContext(), this))
                .build().inject(this);
    }

    @Override
    protected void updateData() {
        /*
         * Please put some module to update new data here, instead of doneRefreshing() method if
         * there is some data to be updated in Server side.
         * I just put doneRefreshing() method because there is no data to be updated from Sever side.
         */
        doneRefreshing();

        Log.i(TAG, "updateData");
    }

    @Override
    protected boolean isLastPage(int pageNum) {
        return (getTotalPageCount() == pageNum) && (getTotalPageCount() >= 1);
    }

    private void requestRepositoryList(final boolean isNew)
            throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if (!isNew) {
            mPresenter.setRepositoryAdapterView(mAdapter);
            mPresenter.setRepositoryAdapterPresenter(mAdapter);
            mPresenter.getRepositoryList(getContext(), SplashActivity.USER_NAME, true);
        }
    }

    public RepositoryContract.Presenter getPresenter() {
        return mPresenter;
    }
}
