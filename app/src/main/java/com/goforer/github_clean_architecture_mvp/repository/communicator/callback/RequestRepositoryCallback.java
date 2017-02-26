package com.goforer.github_clean_architecture_mvp.repository.communicator.callback;

import com.goforer.github_clean_architecture_mvp.presentation.contract.RepositoryAdapterContract.View;
import com.goforer.github_clean_architecture_mvp.presentation.model.data.Repository;
import com.goforer.github_clean_architecture_mvp.presentation.model.event.ResponseRepositoryEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Communicates responses from Server or offline requests.
 * One and only one method will be invoked in response to a given request.
 */
public class RequestRepositoryCallback implements Callback<List<Repository>> {
    private ResponseRepositoryEvent mEvent;

    private View mView;

    protected RequestRepositoryCallback(ResponseRepositoryEvent event, View view) {
        mEvent = event;
        mView = view;
    }

    @Override
    public void onResponse(Call<List<Repository>> call,
                           retrofit2.Response<List<Repository>> response) {
        if (mEvent != null) {
            mEvent.setResponseClient(response.body());
            mEvent.parseInResponse();
            mEvent.setView(mView);
            EventBus.getDefault().post(mEvent);
        }
    }

    @Override
    public void onFailure(Call<List<Repository>> call, Throwable t) {
        if (mEvent != null) {
            EventBus.getDefault().post(mEvent);
        }
    }
}