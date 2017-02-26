package com.goforer.github_clean_architecture_mvp.repository.request;

import com.goforer.github_clean_architecture_mvp.domain.repository.UserReq;
import com.goforer.github_clean_architecture_mvp.presentation.contract.SplashContract.View;
import com.goforer.github_clean_architecture_mvp.presentation.model.data.User;
import com.goforer.github_clean_architecture_mvp.presentation.model.event.ResponseUserEvent;
import com.goforer.github_clean_architecture_mvp.repository.communicator.RequestClient;
import com.goforer.github_clean_architecture_mvp.repository.communicator.callback.RequestUserCallback;

import retrofit2.Call;
import retrofit2.Response;

public class UserReqImpl implements UserReq {
    @Override
    public void getProfile(View view, String userName, ResponseUserEvent event)  {

        Call<User> call = RequestClient.INSTANCE.getRequestMethod().getUser(userName);
        call.enqueue(new RequestUserCallback(event, view) {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                super.onResponse(call, response);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                super.onFailure(call, t);
            }
        });
    }

}
