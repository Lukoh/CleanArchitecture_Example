package com.goforer.clean_architecture.domain.repository;

import android.content.Context;

import com.goforer.clean_architecture.presentation.contract.RepositoryAdapterContract.View;
import com.goforer.clean_architecture.presentation.model.event.ResponseRepositoryEvent;

public interface RepositoryReq {
    void getRepositoryList(Context context, View view, String userName, boolean enabledSort,
                           ResponseRepositoryEvent event);
}
