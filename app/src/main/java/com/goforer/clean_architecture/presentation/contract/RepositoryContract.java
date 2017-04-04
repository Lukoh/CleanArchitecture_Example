package com.goforer.clean_architecture.presentation.contract;

import android.content.Context;

import com.goforer.base.presentation.presenter.BasePresenter;
import com.goforer.base.presentation.view.BaseView;

public interface RepositoryContract {
    interface View extends BaseView<Presenter> {
    }

    interface Presenter extends BasePresenter {
        void setRepositoryAdapterPresenter(RepositoryAdapterContract.Presenter adapterPresenter);

        @SuppressWarnings("unused")
        RepositoryAdapterContract.Presenter getRepositoryAdapterPresenter();

        void setRepositoryAdapterView(RepositoryAdapterContract.View adapterView);

        @SuppressWarnings("unused")
        RepositoryAdapterContract.View getRepositoryAdapterView();

        void getRepositoryList(Context context, String userName, boolean enabledSort);

        @SuppressWarnings("unused")
        void clear();
    }
}
