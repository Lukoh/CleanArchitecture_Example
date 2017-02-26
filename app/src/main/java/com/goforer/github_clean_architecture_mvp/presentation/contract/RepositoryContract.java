package com.goforer.github_clean_architecture_mvp.presentation.contract;

import com.goforer.base.presentation.presenter.BasePresenter;
import com.goforer.base.presentation.view.BaseView;

public interface RepositoryContract {
    interface View extends BaseView<Presenter> {
    }

    interface Presenter extends BasePresenter {
        void setRepositoryAdapterPresenter(RepositoryAdapterContract.Presenter adapterPresenter);

        void setRepositoryAdapterView(RepositoryAdapterContract.View adapterView);

        void getRepositoryList(String userName, boolean enabledSort);

        void clear();
    }
}
