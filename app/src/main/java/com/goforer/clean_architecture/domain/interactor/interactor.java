package com.goforer.clean_architecture.domain.interactor;

import com.goforer.clean_architecture.presentation.contract.RepositoryAdapterContract;

import java.util.List;

public interface interactor {
    void onSorted(RepositoryAdapterContract.View view, List<?> items);
}