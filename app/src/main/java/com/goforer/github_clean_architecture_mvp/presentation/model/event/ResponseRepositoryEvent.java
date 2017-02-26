package com.goforer.github_clean_architecture_mvp.presentation.model.event;

import com.goforer.base.presentation.model.event.ResponseEvent;
import com.goforer.github_clean_architecture_mvp.presentation.contract.RepositoryAdapterContract.View;
import com.goforer.github_clean_architecture_mvp.presentation.model.data.Repository;

import java.util.List;

public class ResponseRepositoryEvent extends ResponseEvent {
    protected List<Repository> mResponse;

    protected View mView;

    @SuppressWarnings("unused")
    public List<Repository> getResponseClient() { return mResponse; }

    public void setResponseClient(List<Repository> responses) { mResponse = responses; }

    public View getView() {
        return mView;
    }

    public void setView(View view) {
        mView = view;
    }
}
