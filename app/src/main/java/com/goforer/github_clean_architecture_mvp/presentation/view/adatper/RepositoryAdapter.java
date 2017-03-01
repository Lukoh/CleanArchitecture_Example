package com.goforer.github_clean_architecture_mvp.presentation.view.adatper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.goforer.base.presentation.utils.CommonUtils;
import com.goforer.base.presentation.view.activity.BaseActivity;
import com.goforer.base.presentation.view.adatper.BaseListAdapter;
import com.goforer.base.presentation.view.fragment.RecyclerFragment;
import com.goforer.base.presentation.view.helper.ItemTouchHelperListener;
import com.goforer.base.presentation.view.holder.BaseViewHolder;
import com.goforer.base.presentation.view.holder.DefaultViewHolder;
import com.goforer.base.presentation.view.view.SquircleImageView;
import com.goforer.github_clean_architecture_mvp.R;
import com.goforer.github_clean_architecture_mvp.presentation.Github_Clean_Architecture;
import com.goforer.github_clean_architecture_mvp.presentation.caller.Caller;
import com.goforer.github_clean_architecture_mvp.presentation.contract.RepositoryAdapterContract;
import com.goforer.github_clean_architecture_mvp.presentation.contract.RepositoryContract;
import com.goforer.github_clean_architecture_mvp.presentation.model.data.Repository;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class  RepositoryAdapter extends BaseListAdapter<Repository>
        implements RepositoryAdapterContract.Presenter, RepositoryAdapterContract.View,
                   ItemTouchHelperListener {
    private Context mContext;

    private RepositoryContract.Presenter mPresenter;

    private RecyclerFragment mFragment;

    private List<Repository> mItems;

    public RepositoryAdapter(Context context, RecyclerFragment fragment, int layoutResId,
                             boolean usedLoadingImage) {
        super(layoutResId);

        setUsedLoadingImage(usedLoadingImage);
        mContext = context;
        mFragment = fragment;

        mItems = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        int count  = super.getItemCount();

        if (isReachedToLastPage()) {
            return count + 1;
        }

        return count;
    }

    @Override
    public int getItemViewType(int position) {
        int itemCount = getItemCount() - 1;

        if (isReachedToLastPage() && position == itemCount) {
            return VIEW_TYPE_FOOTER;
        }

        return VIEW_TYPE_ITEM;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {
        View view;

        switch (type) {
            case VIEW_TYPE_FOOTER:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_last_item,
                        viewGroup, false);
                return new DefaultViewHolder(view);
            default:
                return super.onCreateViewHolder(viewGroup, type);
        }
    }

    @Override
    protected BaseViewHolder createViewHolder(ViewGroup viewGroup, View view, int type) {
        return new RepositoryViewHolder(view, ((BaseActivity)mContext).resumed());
    }

    @Override
    public void onBindViewHolder(final BaseViewHolder viewHolder, int position) {
        switch (getItemViewType(position)){
            case VIEW_TYPE_FOOTER:
            case VIEW_TYPE_LOADING:
                return;
            default:
                super.onBindViewHolder(viewHolder, position);
        }
    }

    @Override
    public void onItemDismiss(int position) {
        getItems().remove(position);
        notifyItemRemoved(position);
        notifyItemChanged(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(getItems(), fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        notifyItemChanged(toPosition);
        notifyItemChanged(fromPosition);

        return true;
    }

    @Override
    public void onItemDrag(int actionState) {
        if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
            mFragment.getRefreshLayout().setRefreshing(false);
            mFragment.getRefreshLayout().setEnabled(false);
        } else if (actionState == ItemTouchHelper.ACTION_STATE_IDLE){
            mFragment.getRefreshLayout().setEnabled(true);
        }
    }

    @Override
    public void start() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void stop() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void setPresenter(RepositoryContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void addItems(final List<Repository> items, boolean isUpdated) {
        if (items != null && !items.isEmpty()) {
            if (isUpdated) {
                mItems.addAll(0, items);
                notifyItemRangeChanged(0, items.size() + 1);
            } else {
                int startIndex = items.size() - 1;
                mItems.addAll(items);
                if (mFragment.getCurrentPage() != 1) {
                    notifyItemRangeChanged(startIndex, items.size() + 1);
                } else {
                    notifyDataSetChanged();
                }
            }
        } else {
            notifyDataSetChanged();
        }

        super.addItems(mItems);

        mFragment.doneRefreshing();
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        CommonUtils.showToastMessage(mContext, errorMessage, Toast.LENGTH_SHORT);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Github_Clean_Architecture.closeApplication();
            }
        }, Toast.LENGTH_SHORT);

    }

    private final static class RepositoryViewHolder extends BaseViewHolder<Repository> {
        private View mView;

        @SuppressWarnings("unused")
        private boolean mIsResumed;

        RepositoryViewHolder(View itemView, boolean isResumed) {
            super(itemView);

            mView = itemView;
            mIsResumed = isResumed;
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void bindItemHolder(final BaseViewHolder holder, @NonNull final Repository repository,
                                   final int position) {
            holder.getView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (repository.getHomepage() == null || "".equals(repository.getHomepage())) {
                        CommonUtils.showToastMessage(getContext(),
                                getContext().getString(R.string.no_homepage), Toast.LENGTH_SHORT);
                        return;
                    }

                    if (mIsResumed) {
                        Caller.INSTANCE.callChromeCustomTabs(getContext(), repository.getHomepage());
                    }
                }
            });

            ((TextView)holder.getView().findViewById(R.id.tv_name))
                    .setText(repository.getName());
            ((TextView)holder.getView().findViewById(R.id.tv_description))
                    .setText(repository.getDescription());
            ((TextView)holder.getView().findViewById(R.id.tv_count))
                    .setText(holder.getContext().getString(R.string.star_count)
                            +  "  " + String.valueOf(repository.getStarCount()));
            ((SquircleImageView)holder.getView().findViewById(R.id.iv_avatar))
                    .setImage(repository.get0wner().getAvartarUrl());
        }

        @Override
        public void onItemSelected() {
            mView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            mView.setBackgroundColor(0);
        }
    }

}
