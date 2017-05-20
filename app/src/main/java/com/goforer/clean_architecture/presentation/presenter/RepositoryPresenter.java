package com.goforer.clean_architecture.presentation.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.goforer.clean_architecture.domain.interactor.interactor;
import com.goforer.clean_architecture.domain.sort.comparator.RepositoryComparator;
import com.goforer.clean_architecture.domain.sort.SortImpl;
import com.goforer.clean_architecture.domain.repository.RepositoryReq;
import com.goforer.clean_architecture.presentation.contract.RepositoryAdapterContract;
import com.goforer.clean_architecture.presentation.contract.RepositoryContract;
import com.goforer.clean_architecture.repository.model.data.Repository;
import com.goforer.clean_architecture.repository.model.event.RepositoryEvent;
import com.goforer.clean_architecture.repository.request.RepositoryReqImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

public class RepositoryPresenter implements RepositoryContract.Presenter, interactor {
    private RepositoryAdapterContract.View mAdapterView;
    @SuppressWarnings("unused")
    private RepositoryAdapterContract.Presenter mAdapterPresenter;

    @Inject
    SortImpl<Repository> mSort;

    @Inject
    public RepositoryPresenter(@NonNull RepositoryContract.View view) {
        view.setPresenter(this);
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
    public RepositoryAdapterContract.Presenter getRepositoryAdapterPresenter() {
        return mAdapterPresenter;
    }

    @Override
    public void setRepositoryAdapterView(RepositoryAdapterContract.View adapterView) {
        mAdapterView = adapterView;
        mAdapterView.setPresenter(this);
    }

    @Override
    public RepositoryAdapterContract.View getRepositoryAdapterView() {
        return mAdapterView;
    }

    @Override
    public void getRepositoryList(Context context, String userName, boolean enabledSort) {
        RepositoryReq request = new RepositoryReqImpl();
        RepositoryEvent event = new RepositoryEvent();
        request.getRepositoryList(context, mAdapterView, userName, enabledSort, event);
    }

    @Override
    public void clear() {

    }

    @Override
    public void onSorted(RepositoryAdapterContract.View view, List<?> items) {
        view.addItems(items, false);
    }


    @SuppressWarnings("unused")
    public RepositoryAdapterContract.Presenter getAdapterPresenter() {
        return mAdapterPresenter;
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RepositoryEvent event) {
        if (event.getResponseClient() == null) {
            event.getView().showError(event.getMessage());

            return;
        }

        if (!event.enabledSort()) {
            event.getView().addItems(event.getResponseClient(), false);

            return;
        }

        if (mSort != null) {
            mSort.setSort(this, event.getView());
            mSort.sort(event.getResponseClient(), new RepositoryComparator());
        }
    }
}
