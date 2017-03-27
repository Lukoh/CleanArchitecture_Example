package com.goforer.clean_architecture.repository.request;

import android.content.Context;

import com.goforer.clean_architecture.domain.repository.RepositoryReq;
import com.goforer.clean_architecture.presentation.contract.RepositoryAdapterContract.View;
import com.goforer.clean_architecture.presentation.model.data.Repository;
import com.goforer.clean_architecture.presentation.model.event.ResponseRepositoryEvent;
import com.goforer.clean_architecture.repository.netcarrier.callback.ReqRepositoryCallback;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class RepositoryReqImpl extends BaseReqImpl implements RepositoryReq {

    public RepositoryReqImpl() {
        super();
    }

    @Override
    public void getRepositoryList(final Context context, final View view, final String userName,
                                  final boolean enabledSort, final ResponseRepositoryEvent event) {
        Call<List<Repository>> call
                = mCallMethod.getRepository(userName);
        call.enqueue(new ReqRepositoryCallback(context, event, view, enabledSort) {
            @Override
            public void onResponse(Call<List<Repository>> call,
                                   Response<List<Repository>> response) {
                super.onResponse(call, response);
            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
                super.onFailure(call, t);
            }
        });
    }
}

