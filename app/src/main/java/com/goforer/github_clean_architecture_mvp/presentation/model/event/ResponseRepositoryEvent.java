package com.goforer.github_clean_architecture_mvp.presentation.model.event;

import android.content.Context;

import com.goforer.base.presentation.model.event.ResponseEvent;
import com.goforer.github_clean_architecture_mvp.presentation.contract.RepositoryAdapterContract.View;
import com.goforer.github_clean_architecture_mvp.presentation.model.data.Repository;

import java.util.List;

public class ResponseRepositoryEvent extends ResponseEvent {
    protected List<Repository> mResponse;

    protected View mView;

    protected Context mContext;

    protected boolean mEnabledSort;

    protected String mMessage;

    @SuppressWarnings("unused")
    public List<Repository> getResponseClient() { return mResponse; }

    public void setResponseClient(List<Repository> responses) { mResponse = responses; }

    public View getView() {
        return mView;
    }

    public void setView(View view) {
        mView = view;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public boolean enabledSort() {
        return mEnabledSort;
    }

    public void setEnableSort(boolean enabledSort) {
        mEnabledSort = enabledSort;
    }

    public String getMessage() {
        return mMessage;
    }
}
