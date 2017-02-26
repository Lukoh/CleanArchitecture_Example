package com.goforer.base.presentation.model.event;

public class ResponseEvent {
    protected String mTag;

    public boolean isMine(String tag){
        return tag == null || tag.equals(mTag);
    }

    public void parseInResponse() {
    }

    public String getTag() { return mTag; }

    public void setTag(String tag) { mTag = tag; }
}
