package com.goforer.clean_architecture.presentation.contract;

import com.goforer.base.presentation.presenter.BasePresenter;
import com.goforer.base.presentation.view.BaseView;

import java.util.List;

public interface RepositoryAdapterContract {
    interface View extends BaseView<RepositoryContract.Presenter> {
        void addItems(List<?> list, boolean isUpdated);

        void showError(String errorMessage);
    }

    interface Presenter extends BasePresenter {
    }
}
