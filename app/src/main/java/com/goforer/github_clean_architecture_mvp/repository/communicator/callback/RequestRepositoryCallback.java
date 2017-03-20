package com.goforer.github_clean_architecture_mvp.repository.communicator.callback;

import android.content.Context;

import com.goforer.github_clean_architecture_mvp.presentation.contract.RepositoryAdapterContract.View;
import com.goforer.github_clean_architecture_mvp.presentation.model.data.Repository;
import com.goforer.github_clean_architecture_mvp.presentation.model.event.ResponseRepositoryEvent;
import com.goforer.github_clean_architecture_mvp.repository.communicator.RequestClient;

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

    private Context mContext;

    private View mView;

    private boolean mEnabledSort;

    protected RequestRepositoryCallback(final Context context, final ResponseRepositoryEvent event,
                                        final View view, final boolean enabledSort) {
        mContext = context;
        mEvent = event;
        mView = view;
        mEnabledSort = enabledSort;
    }

    @Override
    public void onResponse(Call<List<Repository>> call,
                           retrofit2.Response<List<Repository>> response) {
        if (mEvent != null) {
            mEvent.setResponseClient(response.body());
            mEvent.setContext(mContext);
            mEvent.setMessage(RequestClient.SUCCESS);
            mEvent.parseInResponse();
            mEvent.setView(mView);
            mEvent.setEnableSort(mEnabledSort);
            EventBus.getDefault().post(mEvent);
        }
    }

    @Override
    public void onFailure(Call<List<Repository>> call, Throwable t) {
        if (mEvent != null) {
            EventBus.getDefault().post(mEvent);
            mEvent.setResponseClient(null);
            mEvent.setContext(mContext);
            mEvent.setMessage(t.getMessage());
            mEvent.setView(mView);
        }
    }
}