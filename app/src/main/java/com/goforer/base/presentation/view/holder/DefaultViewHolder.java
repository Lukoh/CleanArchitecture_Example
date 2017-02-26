package com.goforer.base.presentation.view.holder;

import android.support.annotation.NonNull;
import android.view.View;

/**
 * The default ViewHolder
 */
public class DefaultViewHolder extends BaseViewHolder<Object> {
    public DefaultViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindItemHolder(BaseViewHolder holder, @NonNull Object obj, int position) {
    }

    @Override
    public void onItemSelected() {
    }

    @Override
    public void onItemClear() {
    }
}
