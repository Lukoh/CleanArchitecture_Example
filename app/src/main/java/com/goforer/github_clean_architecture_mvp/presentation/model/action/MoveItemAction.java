package com.goforer.github_clean_architecture_mvp.presentation.model.action;

public class MoveItemAction {
    public static final int ITEM_MOVED_START = 0;
    public static final int ITEM_MOVED_END = 1;

    private int mType;

    public MoveItemAction() {
    }

    public void setType(int type) {
        mType = type;
    }

    public int getType() {
        return mType;
    }
}
