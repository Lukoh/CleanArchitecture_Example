package com.goforer.base.presentation.view.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.goforer.base.presentation.view.adatper.BaseAdapter;

import butterknife.ButterKnife;

/**
 * A BaseViewHolder describes an item view and metadata about its place within the RecyclerView.
 *
 * <p>{@link BaseAdapter} implementations should subclass ViewHolder and add fields for caching
 * potentially expensive {@link View#findViewById(int)} results.</p>
 *
 * <p>See {@link RecyclerView.ViewHolder} if you'd like to get more.</p>
 */
public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder implements ItemHolderBinder<T> {
    private Context mContext;
    private View mView;


    public BaseViewHolder(View itemView) {
        super(itemView);
        mContext = itemView.getContext();
        mView = itemView;
        ButterKnife.bind(this, itemView);
    }

    public Context getContext() {
        return mContext;
    }

    public View getView() {
        return mView;
    }
}
