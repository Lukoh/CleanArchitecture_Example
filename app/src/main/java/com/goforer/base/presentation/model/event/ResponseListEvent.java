package com.goforer.base.presentation.model.event;

import java.util.List;

public class ResponseListEvent<T> extends ResponseEvent {
    protected List<T> mResponse;
    private boolean mIsNew = true;

    public ResponseListEvent() {
        this(false);
    }

    public ResponseListEvent(boolean isNew) {
        mIsNew = isNew;
    }

    @SuppressWarnings("unused")
    public List<T> getResponseClient() { return mResponse; }

    public void setResponseClient(List<T> responses) { mResponse = responses; }

    public boolean isNew() {
        return mIsNew;
    }
}
