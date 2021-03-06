package com.goforer.base.presentation.view.helper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.goforer.base.presentation.view.holder.ItemHolderBinder;
import com.goforer.clean_architecture.R;

import javax.inject.Inject;

public class RecyclerItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private static final float ALPHA_FULL = 1.0f;

    private Context mContext;

    private final ItemTouchHelperListener mHelperListener;

    private Drawable mBackground;
    private Drawable mDeleteIcon;

    private int mDeleteIconMargin;
    private int mBgColor;

    private boolean mInitiated;

    @SuppressWarnings("unused")
    public RecyclerItemTouchHelperCallback(Context context, ItemTouchHelperListener listener) {
        this(context, listener, Color.BLUE);
    }

    @Inject
    public RecyclerItemTouchHelperCallback(Context context, ItemTouchHelperListener listener, int bgColor) {
        mContext = context;
        mHelperListener = listener;
        mBgColor = bgColor;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        // Set movement flags based on the layout manager
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            final int swipeFlags = 0;
            return makeMovementFlags(dragFlags, swipeFlags);
        } else {
            final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            final int swipeFlags = ItemTouchHelper.START;
            return makeMovementFlags(dragFlags, swipeFlags);
        }
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder source, RecyclerView.ViewHolder target) {
        if (source.getItemViewType() != target.getItemViewType()) {
            return false;
        }

        // Notify the adapter of the move
        mHelperListener.onItemMove(source.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        // Notify the adapter of the dismissal
        mHelperListener.onItemDismiss(viewHolder.getAdapterPosition());
    }

    @Override
    public void onChildDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            View itemView = viewHolder.itemView;

            // not sure why, but this method get's called for viewholder that are already swiped away
            if (viewHolder.getAdapterPosition() == -1) {
                // not interested in those
                return;
            }

            if (!mInitiated) {
                init();
            }

            // draw red background
            mBackground.setBounds(itemView.getRight() + (int) dX, itemView.getTop(), itemView.getRight(), itemView.getBottom());
            mBackground.draw(canvas);

            // draw delete-icon
            int itemHeight = itemView.getBottom() - itemView.getTop();
            int intrinsicWidth = mDeleteIcon.getIntrinsicWidth();
            int intrinsicHeight = mDeleteIcon.getIntrinsicWidth();

            int xMarkLeft = itemView.getRight() - mDeleteIconMargin - intrinsicWidth;
            int xMarkRight = itemView.getRight() - mDeleteIconMargin;
            int xMarkTop = itemView.getTop() + (itemHeight - intrinsicHeight)/2;
            int xMarkBottom = xMarkTop + intrinsicHeight;

            mDeleteIcon.setBounds(xMarkLeft, xMarkTop, xMarkRight, xMarkBottom);
            mDeleteIcon.draw(canvas);


            // Fade out the view as it is swiped out of the parent's bounds
            final float alpha = ALPHA_FULL - Math.abs(dX) / (float) viewHolder.itemView.getWidth();
            viewHolder.itemView.setAlpha(alpha);
            viewHolder.itemView.setTranslationX(dX);

        } else {
            super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        // We only want the active item to change
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            if (viewHolder instanceof ItemHolderBinder) {
                // Let the view holder know that this item is being moved or dragged
                ItemHolderBinder itemViewHolder = (ItemHolderBinder) viewHolder;
                itemViewHolder.onItemSelected();
            }
        }

        if ((actionState == ItemTouchHelper.ACTION_STATE_DRAG) || (actionState == ItemTouchHelper.ACTION_STATE_IDLE)) {
            mHelperListener.onItemDrag(actionState);
        }

        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);

        viewHolder.itemView.setAlpha(ALPHA_FULL);

        if (viewHolder instanceof ItemHolderBinder) {
            // Tell the view holder it's time to restore the idle state
            ItemHolderBinder itemViewHolder = (ItemHolderBinder) viewHolder;
            itemViewHolder.onItemClear();
        }
    }

    private void init() {
        mBackground = new ColorDrawable(mBgColor);
        mDeleteIcon = ContextCompat.getDrawable(mContext, R.drawable.ic_delete_item);
        mDeleteIconMargin = (int) mContext.getResources().getDimension(R.dimen.helper_icon_clear_margin);
        mInitiated = true;
    }
}
