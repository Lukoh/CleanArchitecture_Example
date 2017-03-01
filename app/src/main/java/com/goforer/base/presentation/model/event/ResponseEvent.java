package com.goforer.base.presentation.model.event;

public class ResponseEvent {
    protected String mTag;
    protected String mMessage;

    public boolean isMine(String tag){
        return tag == null || tag.equals(mTag);
    }

    public void parseInResponse() {
    }

    public String getTag() { return mTag; }

    public void setTag(String tag) { mTag = tag; }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }
}
