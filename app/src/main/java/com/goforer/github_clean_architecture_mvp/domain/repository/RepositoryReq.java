package com.goforer.github_clean_architecture_mvp.domain.repository;

import android.content.Context;

import com.goforer.github_clean_architecture_mvp.presentation.contract.RepositoryAdapterContract.View;
import com.goforer.github_clean_architecture_mvp.presentation.model.event.ResponseRepositoryEvent;

public interface RepositoryReq {
    void getRepositoryList(Context context, View view, String userName, boolean enabledSort,
                           ResponseRepositoryEvent event);
}
