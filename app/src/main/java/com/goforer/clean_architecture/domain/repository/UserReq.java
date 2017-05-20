package com.goforer.clean_architecture.domain.repository;

import com.goforer.clean_architecture.presentation.contract.SplashContract.View;
import com.goforer.clean_architecture.repository.model.event.ResponseUserEvent;

public interface UserReq {
    void getProfile(View view, String userName, ResponseUserEvent event);
}
