package com.goforer.github_clean_architecture_mvp.presentation.contract;

import com.goforer.base.presentation.presenter.BasePresenter;
import com.goforer.base.presentation.view.BaseView;
import com.goforer.github_clean_architecture_mvp.presentation.model.data.Repository;

import java.util.List;

public interface RepositoryAdapterContract {
    interface View extends BaseView<RepositoryContract.Presenter> {
        void addItems(List<Repository> list, boolean isUpdated);

        void showErrorMessage(String errorMessage);
    }

    interface Presenter extends BasePresenter {
    }
}
