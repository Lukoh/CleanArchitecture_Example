package com.goforer.clean_architecture.repository.netcarrier.callback;

import android.content.Context;

import com.goforer.clean_architecture.presentation.contract.RepositoryAdapterContract.View;
import com.goforer.clean_architecture.repository.model.data.Repository;
import com.goforer.clean_architecture.repository.model.event.ResponseRepositoryEvent;
import com.goforer.clean_architecture.repository.request.BaseReqImpl;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Communicates responses from Server or offline requests.
 * One and only one method will be invoked in response to a given request.
 */
public class ReqRepositoryCallback implements Callback<List<Repository>> {
    private ResponseRepositoryEvent mEvent;

    private Context mContext;

    private View mView;

    private boolean mEnabledSort;

    protected ReqRepositoryCallback(final Context context, final ResponseRepositoryEvent event,
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
            mEvent.setMessage(BaseReqImpl.SUCCESS);
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