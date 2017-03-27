package com.goforer.clean_architecture.repository.netcarrier.callback;

import com.goforer.clean_architecture.presentation.contract.SplashContract.View;
import com.goforer.clean_architecture.presentation.model.data.User;
import com.goforer.clean_architecture.presentation.model.event.ResponseUserEvent;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Communicates responses from Server or offline requests.
 * One and only one method will be invoked in response to a given request.
 */
public class ReqUserCallback implements Callback<User> {
    private ResponseUserEvent mEvent;
    private View mView;

    protected ReqUserCallback(ResponseUserEvent event, View view) {
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
