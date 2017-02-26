package com.goforer.github_clean_architecture_mvp.repository.request;

import com.goforer.github_clean_architecture_mvp.domain.repository.RepositoryReq;
import com.goforer.github_clean_architecture_mvp.presentation.contract.RepositoryAdapterContract.View;
import com.goforer.github_clean_architecture_mvp.presentation.model.data.Repository;
import com.goforer.github_clean_architecture_mvp.presentation.model.event.ResponseRepositoryEvent;
import com.goforer.github_clean_architecture_mvp.repository.communicator.RequestClient;
import com.goforer.github_clean_architecture_mvp.repository.communicator.callback.RequestRepositoryCallback;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class RepositoryReqImpl implements RepositoryReq {
    @Override
    public void getRepositoryList(View view, String userName, ResponseRepositoryEvent event) {
        Call<List<Repository>> call
                = RequestClient.INSTANCE.getRequestMethod().getRepository(userName);
        call.enqueue(new RequestRepositoryCallback(event, view) {
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
