package com.goforer.github_clean_architecture_mvp.domain.repository;

import com.goforer.github_clean_architecture_mvp.presentation.contract.SplashContract.View;
import com.goforer.github_clean_architecture_mvp.presentation.model.event.ResponseUserEvent;

public interface UserReq {
    void getProfile(View view, String userName, ResponseUserEvent event);
}
