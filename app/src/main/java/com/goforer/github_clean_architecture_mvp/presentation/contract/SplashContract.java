package com.goforer.github_clean_architecture_mvp.presentation.contract;

import android.content.Context;

import com.goforer.base.presentation.presenter.BasePresenter;
import com.goforer.base.presentation.view.BaseView;
import com.goforer.github_clean_architecture_mvp.presentation.model.data.User;

public interface SplashContract {
    interface View extends BaseView<SplashContract.Presenter> {
        void setUserProfile(User user);

        void showError(Context context, String errorMessage);
    }

    interface Presenter extends BasePresenter {
        void getUser(String userName);
    }
}
