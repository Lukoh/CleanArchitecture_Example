package com.goforer.github_clean_architecture_mvp.repository.communicator.callback;

import com.goforer.github_clean_architecture_mvp.presentation.contract.SplashContract.View;
import com.goforer.github_clean_architecture_mvp.presentation.model.data.User;
import com.goforer.github_clean_architecture_mvp.presentation.model.event.ResponseUserEvent;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Communicates responses from Server or offline requests.
 * One and only one method will be invoked in response to a given request.
 */
public class RequestUserCallback implements Callback<User> {
    private ResponseUserEvent mEvent;
    private View mView;

    protected RequestUserCallback(ResponseUserEvent event, View view) {
        mEvent = event;
        mView = view;
    }

    @Override
    public void onResponse(Call<User> call,
                           retrofit2.Response<User> response) {
        if (mEvent != null) {
            mEvent.setResponseClient(response.body());
            mEvent.parseInResponse();
            mEvent.setView(mView);
            EventBus.getDefault().post(mEvent);
        }
    }

    @Override
    public void onFailure(Call<User> call, Throwable t) {
        if (mEvent != null) {
            mEvent.setResponseClient(new User());
            EventBus.getDefault().post(mEvent);
        }
    }
}
