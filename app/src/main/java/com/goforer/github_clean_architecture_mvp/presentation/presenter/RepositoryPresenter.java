package com.goforer.github_clean_architecture_mvp.presentation.presenter;

import android.support.annotation.NonNull;

import com.goforer.github_clean_architecture_mvp.domain.interactor.Sort;
import com.goforer.github_clean_architecture_mvp.domain.sort.comparator.RepositoryComparator;
import com.goforer.github_clean_architecture_mvp.domain.sort.SortImpl;
import com.goforer.github_clean_architecture_mvp.domain.repository.RepositoryReq;
import com.goforer.github_clean_architecture_mvp.presentation.contract.RepositoryAdapterContract;
import com.goforer.github_clean_architecture_mvp.presentation.contract.RepositoryContract;
import com.goforer.github_clean_architecture_mvp.presentation.contract.RepositoryContract.View;
import com.goforer.github_clean_architecture_mvp.presentation.model.data.Repository;
import com.goforer.github_clean_architecture_mvp.presentation.model.event.RepositoryEvent;
import com.goforer.github_clean_architecture_mvp.repository.request.RepositoryReqImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class RepositoryPresenter implements RepositoryContract.Presenter {
    private RepositoryAdapterContract.View mAdapterView;
    @SuppressWarnings("unused")
    private RepositoryAdapterContract.Presenter mAdapterPresenter;

    private final View mView;

    private Sort<Repository> mSort;

    public RepositoryPresenter(@NonNull RepositoryContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void stop() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void setRepositoryAdapterPresenter(RepositoryAdapterContract.Presenter adapterPresenter) {
        mAdapterPresenter = adapterPresenter;
    }

    @Override
    public void setRepositoryAdapterView(RepositoryAdapterContract.View adapterView) {
        mAdapterView = adapterView;
        mAdapterView.setPresenter(this);
    }

    @Override
    public void getRepositoryList(String userName, boolean enabledSort) {
        if (enabledSort) {
            mSort = new SortImpl<>();
        }

        RepositoryReq request = new RepositoryReqImpl();
        RepositoryEvent event = new RepositoryEvent();
        request.getRepositoryList(mAdapterView, userName, event);
    }

    @Override
    public void clear() {

    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RepositoryEvent event) {
        event.getView().addItems(mSort.sort(event.getResponseClient(), new RepositoryComparator()),
                false);
    }
}
