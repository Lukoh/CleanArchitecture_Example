package com.goforer.clean_architecture.presentation.view.adatper;

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
import com.goforer.clean_architecture.R;
import com.goforer.clean_architecture.CleanArchitectureApplication;
import com.goforer.clean_architecture.presentation.caller.Caller;
import com.goforer.clean_architecture.presentation.contract.RepositoryAdapterContract;
import com.goforer.clean_architecture.presentation.contract.RepositoryContract;
import com.goforer.clean_architecture.presentation.model.data.Repository;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class  RepositoryAdapter extends BaseListAdapter<Repository>
        implements RepositoryAdapterContract.Presenter, RepositoryAdapterContract.View,
                   ItemTouchHelperListener {
    private Context mContext;

    @SuppressWarnings("unused")
    private RepositoryContract.Presenter mPresenter;

    private RecyclerFragment mFragment;

    private List mItems;

    @Inject
    public RepositoryAdapter(Context context, RecyclerFragment fragment) {
        super(R.layout.list_repository_item);

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
    @SuppressWarnings("unchecked")
    public void addItems(final List<?> items, boolean isUpdated) {
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
    public void showError(String errorMessage) {
        CommonUtils.showToastMessage(mContext, errorMessage, Toast.LENGTH_SHORT);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                CleanArchitectureApplication.closeApplication();
            }
        }, Toast.LENGTH_SHORT);

    }

    public void setEnableLoadingImage(boolean usedLoadingImage) {
        setUsedLoadingImage(usedLoadingImage);
    }

    @SuppressWarnings("WeakerAccess")
    final static class RepositoryViewHolder extends BaseViewHolder<Repository> {
        private View mView;

        @SuppressWarnings("unused")
        private boolean mIsResumed;

        RepositoryViewHolder(View itemView, boolean isResumed) {
            super(itemView);

            mView = itemView;
            mIsResumed = isResumed;
        }

        @SuppressWarnings("ConstantConditions")
        @SuppressLint("SetTextI18n")
        @Override
        public void bindItemHolder(final BaseViewHolder holder, @NonNull final Repository repository,
                                   final int position) {
            holder.getView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (repository.homepage() == null || "".equals(repository.homepage())) {
                        CommonUtils.showToastMessage(getContext(),
                                getContext().getString(R.string.no_homepage), Toast.LENGTH_SHORT);
                        return;
                    }

                    if (mIsResumed) {
                        Caller.INSTANCE.callChromeCustomTabs(getContext(), repository.homepage());
                    }
                }
            });

            ((TextView)holder.getView().findViewById(R.id.tv_name))
                    .setText(repository.name());
            ((TextView)holder.getView().findViewById(R.id.tv_description))
                    .setText(repository.description());
            ((TextView)holder.getView().findViewById(R.id.tv_count))
                    .setText(holder.getContext().getString(R.string.star_count)
                            +  "  " + String.valueOf(repository.stargazers_count()));
            ((SquircleImageView)holder.getView().findViewById(R.id.iv_avatar))
                    .setImage(repository.owner().avatar_url());
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
