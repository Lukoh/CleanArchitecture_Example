package com.goforer.base.presentation.view.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * A ItemHolderBinder describes an item view and metadata about its place within the RecyclerView.
 *
 * <p>All ViewHolder extended {@link BaseViewHolder} implementations should subclass ItemHolderBinder
 * and implements bindItemHolder method to to display the item at the specified position.</p>
 *
 */
public interface ItemHolderBinder<T> {
    /**
     * Called by RecyclerView to display the item at the specified position. This method
     * should update the contents of the item's view to reflect the item at the given position.
     *
     * @param holder
     * @param item The ItemHolder which should be updated to represent the contents of the
     *        item at the given position in the data set
     * @param position The position of the item within the adapter's data set.
     *
     */
    void bindItemHolder(BaseViewHolder holder, @NonNull T item, int position);

    /**
     * Called when the {@link ItemTouchHelper} first registers an item as being moved or swiped.
     * Implementations should update the item view to indicate it's active state.
     */
    void onItemSelected();


    /**
     * Called when the {@link ItemTouchHelper} has completed the move or swipe, and the active item
     * state should be cleared.
     */
    void onItemClear();
}

