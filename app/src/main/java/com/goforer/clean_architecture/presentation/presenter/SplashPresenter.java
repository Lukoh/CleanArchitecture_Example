package com.goforer.clean_architecture.presentation.presenter;

import android.support.annotation.NonNull;

import com.goforer.clean_architecture.domain.repository.UserReq;
import com.goforer.clean_architecture.presentation.contract.SplashContract;
import com.goforer.clean_architecture.presentation.contract.SplashContract.View;
import com.goforer.clean_architecture.presentation.model.event.UserProfileEvent;
import com.goforer.clean_architecture.repository.request.UserReqImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

public class SplashPresenter implements SplashContract.Presenter {
    private final View mView;

    @Inject
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
        if (event.getResponseClient() == null) {
            event.getView().showError(null, event.getMessage());

            return;
        }

        event.getView().setUserProfile(event.getResponseClient());
    }
}
