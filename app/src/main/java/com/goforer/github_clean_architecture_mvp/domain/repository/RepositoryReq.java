package com.goforer.github_clean_architecture_mvp.domain.repository;

import com.goforer.github_clean_architecture_mvp.presentation.contract.RepositoryAdapterContract.View;
import com.goforer.github_clean_architecture_mvp.presentation.model.event.ResponseRepositoryEvent;

public interface RepositoryReq {
    void getRepositoryList(View view, String userName, ResponseRepositoryEvent event);
}
