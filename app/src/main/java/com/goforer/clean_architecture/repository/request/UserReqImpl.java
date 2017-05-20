package com.goforer.clean_architecture.repository.request;

import com.goforer.clean_architecture.domain.repository.UserReq;
import com.goforer.clean_architecture.presentation.contract.SplashContract.View;
import com.goforer.clean_architecture.repository.model.data.User;
import com.goforer.clean_architecture.repository.model.event.ResponseUserEvent;
import com.goforer.clean_architecture.repository.netcarrier.callback.ReqUserCallback;

import retrofit2.Call;
import retrofit2.Response;

public class UserReqImpl extends BaseReqImpl implements UserReq {

    public UserReqImpl() {
        super();
    }

    @Override
    public void getProfile(final View view, final String userName, final ResponseUserEvent event)  {

        Call<User> call = mCallMethod.getUser(userName);
        call.enqueue(new ReqUserCallback(event, view) {
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
