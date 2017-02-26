package com.goforer.base.presentation.view.adatper;

import android.support.v7.widget.RecyclerView;

import com.goforer.base.presentation.view.holder.BaseViewHolder;
import com.goforer.base.presentation.view.holder.ItemHolderBinder;

import java.util.List;

public abstract class BaseListAdapter<T> extends BaseAdapter {
    private boolean mIsReachedToLastItem = false;
    private boolean mIsReachedToLastPage = false;
    private boolean mIsEmptyItems = false;
    private boolean mIsLoadingItems = false;
    private boolean mUsedLoadingImage = false;

    private List<T> mItems;

    public BaseListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    public int getItemCount() {
        if (mItems != null) {
            return mItems.size();
        }

        return 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (holder instanceof ItemHolderBinder) {
            if (position >= mItems.size()) {
                return;
            }

            T item = mItems.get(position);
            if (item != null) {
                ((ItemHolderBinder<T>) holder).bindItemHolder(holder, item, position);
            }
        }
    }

    public void addItems(List<T> items) {
        mItems = items;
    }

    /**
     * Set true if the item is reached to the last.
     *
     * @param isReachedToLast true if the item is reached to the last
     */
    public void setReachedToLastItem(boolean isReachedToLast) {
        mIsReachedToLastItem = isReachedToLast;
    }

    /**
     * Set true if the page is reached to the last and notify any registered observers that the item
     * reflected at last item in last page has been newly inserted for last footer.
     *
     * @param isReachedToLast true if the page is reached to the last
     */
    public void setReachedToLastPage(boolean isReachedToLast) {
        mIsReachedToLastPage = isReachedToLast;
        if (isReachedToLast) {
            setReachedToLastItem(false);
            notifyItemInserted(mItems.size());
        }
    }

    public List<T> getItems() {
        return mItems;
    }

    /**
     * Set true if the items is empty.
     *
     * @param isEmptyItems true if the items is empty
     */
    public void setEmptyItems(boolean isEmptyItems) {
        mIsEmptyItems = isEmptyItems;
    }

    /**
     * Set true if the items is loading.
     *
     * @param isLoadingItems true if the items is loading
     */
    public void setLoadingItems(boolean isLoadingItems) {
        mIsLoadingItems = isLoadingItems;
    }

    /**
     * Set true if the loading image is used.
     *
     * @param usedLoadingImage true if the loading image is used
     */
    public void setUsedLoadingImage(boolean usedLoadingImage) {
        mUsedLoadingImage = usedLoadingImage;
    }

    /**
     * Scroll to the specified adapter position.
     * Actual position of the item on the screen depends on the LayoutManager implementation.
     *
     * @param layoutManager The currently bound LayoutManager
     * @param position Scroll to this adapter position
     */
    public boolean moveSelectedPosition(RecyclerView.LayoutManager layoutManager, int position) {
        if (position >= 0 && position < getItemCount()) {
            layoutManager.scrollToPosition(position);
            return true;
        }

        return false;
    }

    /**
     * Check if the item is reached to the last.
     *
     * @return true if the item or page is reached to the last
     */
    public boolean isReachedToLastItem() {
        return mIsReachedToLastItem;
    }

    /**
     * Check if the page is reached to the last.
     *
     * @return true if the item or page is reached to the last
     */
    public boolean isReachedToLastPage() {

        return mIsReachedToLastPage;
    }

    /**
     * Check if the items is empty.
     *
     * @return true if the items is empty
     */
    public boolean isEmptyItems() {
        return mIsEmptyItems;
    }

    /**
     * Check if the items is loading.
     *
     * @return true if the items is loading
     */
    public boolean isLoadingItems() {
        return mIsLoadingItems;
    }

    /**
     * Check if the loading image is used to the list as an item.
     *
     * @return true if the loading image is used
     */
    public boolean usedLoadImage() {
        return mUsedLoadingImage;
    }
}