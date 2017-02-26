package com.goforer.github_clean_architecture_mvp.presentation.view.view.drawer.item;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;

import com.goforer.github_clean_architecture_mvp.presentation.view.view.drawer.holder.CountPanelViewHolder;
import com.mikepenz.materialdrawer.holder.ColorHolder;
import com.mikepenz.materialdrawer.holder.ImageHolder;
import com.mikepenz.materialdrawer.holder.StringHolder;
import com.mikepenz.materialdrawer.model.BaseDrawerItem;
import com.mikepenz.materialdrawer.util.DrawerUIUtils;
import com.mikepenz.materialize.util.UIUtils;

public abstract class CountPanelDrawerItem<T, VH extends CountPanelViewHolder>
        extends BaseDrawerItem<T, VH> {
    private StringHolder count;

    private ColorHolder countTextColor;

    public T withCount(String count) {
        this.count = new StringHolder(count);
        return (T) this;
    }

    public T withCount(@StringRes int countRes) {
        this.count = new StringHolder(countRes);
        return (T) this;
    }

    public T withCountTextColor(@ColorInt int color) {
        this.countTextColor = ColorHolder.fromColor(color);
        return (T) this;
    }

    public T withCountTextColorRes(@ColorRes int colorRes) {
        this.countTextColor = ColorHolder.fromColorRes(colorRes);
        return (T) this;
    }


    public StringHolder getCount() {
        return count;
    }

    public ColorHolder getCountTextColor() {
        return countTextColor;
    }

    /**
     * a helper method to have the logic for all secondaryDrawerItems only once
     *
     * @param viewHolder
     */
    protected void bindViewHelper(CountPanelViewHolder viewHolder) {
        Context context = viewHolder.itemView.getContext();

        //set the identifier from the drawerItem here. It can be used to run tests
        viewHolder.itemView.setId(hashCode());

        //set the item selected if it is
        viewHolder.itemView.setSelected(isSelected());

        //set the item enabled if it is
        viewHolder.itemView.setEnabled(isEnabled());

        //
        viewHolder.itemView.setTag(this);

        //get the correct color for the background
        int selectedColor = getSelectedColor(context);
        //get the correct color for the text
        int color = getColor(context);
        ColorStateList selectedTextColor = getTextColorStateList(color,
                getSelectedTextColor(context));
        //get the correct color for the icon
        int iconColor = getIconColor(context);
        int selectedIconColor = getSelectedIconColor(context);

        //set the background for the item
        UIUtils.setBackground(viewHolder.getView(), UIUtils.getSelectableBackground(
                context, selectedColor, true));
        //set the text for the name
        StringHolder.applyTo(this.getName(), viewHolder.getName());
        //set the text for the count or hide
        StringHolder.applyToOrHide(this.getCount(), viewHolder.getCount());

        //set the colors for textViews
        viewHolder.getName().setTextColor(selectedTextColor);
        //set the count text color
        ColorHolder.applyToOr(getCountTextColor(), viewHolder.getCount(), selectedTextColor);

        //define the typeface for our textViews
        if (getTypeface() != null) {
            viewHolder.getName().setTypeface(getTypeface());
            viewHolder.getDescription().setTypeface(getTypeface());
            viewHolder.getCount().setTypeface(getTypeface());
        }

        //get the drawables for our icon and set it
        Drawable icon = ImageHolder.decideIcon(getIcon(), context, iconColor, isIconTinted(), 1);
        Drawable selectedIcon = ImageHolder.decideIcon(getSelectedIcon(), context, selectedIconColor,
                isIconTinted(), 1);
        ImageHolder.applyMultiIconTo(icon, iconColor, selectedIcon, selectedIconColor,
                isIconTinted(), viewHolder.getIcon());

        //for android API 17 --> Padding not applied via xml
        DrawerUIUtils.setDrawerVerticalPadding(viewHolder.getView(), level);
    }
}
