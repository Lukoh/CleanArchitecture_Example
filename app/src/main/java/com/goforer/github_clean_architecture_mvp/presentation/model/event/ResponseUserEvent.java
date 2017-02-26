package com.goforer.github_clean_architecture_mvp.presentation.model.event;

import com.goforer.base.presentation.model.event.ResponseEvent;
import com.goforer.github_clean_architecture_mvp.presentation.contract.SplashContract.View;
import com.goforer.github_clean_architecture_mvp.presentation.model.data.User;

public class ResponseUserEvent extends ResponseEvent{
    protected User mResponse;

    protected View mView;

    @SuppressWarnings("unused")
    public User getResponseClient() { return mResponse; }

    public void setResponseClient(User responses) { mResponse = responses; }

    public View getView() {
        return mView;
    }

    public void setView(View view) {
        mView = view;
    }
}
