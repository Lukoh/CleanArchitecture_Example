package com.goforer.github_clean_architecture_mvp.domain.interactor;

import com.goforer.github_clean_architecture_mvp.presentation.contract.RepositoryAdapterContract;

import java.util.List;

public interface interactor {
    void onSorted(RepositoryAdapterContract.View view, List<?> items);
}