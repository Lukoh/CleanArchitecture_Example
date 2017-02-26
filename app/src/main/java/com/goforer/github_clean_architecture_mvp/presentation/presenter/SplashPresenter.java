package com.goforer.github_clean_architecture_mvp.presentation.presenter;

import android.support.annotation.NonNull;

import com.goforer.github_clean_architecture_mvp.domain.repository.UserReq;
import com.goforer.github_clean_architecture_mvp.presentation.contract.SplashContract;
import com.goforer.github_clean_architecture_mvp.presentation.contract.SplashContract.View;
import com.goforer.github_clean_architecture_mvp.presentation.model.event.UserProfileEvent;
import com.goforer.github_clean_architecture_mvp.repository.request.UserReqImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SplashPresenter implements SplashContract.Presenter {
    private final View mView;

    public SplashPresenter(@NonNull View view) {
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
    public void getUser(String userName) {
        UserReq request = new UserReqImpl();
        UserProfileEvent event = new UserProfileEvent();
        request.getProfile(mView, userName, event);
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(UserProfileEvent event) {
        event.getView().setUserProfile(event.getResponseClient());
    }
}
