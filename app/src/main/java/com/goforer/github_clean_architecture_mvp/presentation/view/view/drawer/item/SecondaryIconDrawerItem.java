package com.goforer.github_clean_architecture_mvp.presentation.view.view.drawer.item;

import android.content.Context;
import android.support.annotation.LayoutRes;

import com.mikepenz.materialdrawer.holder.ColorHolder;

public class SecondaryIconDrawerItem extends PrimaryIconDrawerItem {

    @Override
    public int getType() {
        return com.mikepenz.materialdrawer.R.id.material_drawer_item_secondary;
    }

    @Override
    @LayoutRes
    public int getLayoutRes() {
        return com.mikepenz.materialdrawer.R.layout.material_drawer_item_secondary;
    }

    /**
     * helper method to decide for the correct color
     * OVERWRITE to get the correct secondary color
     *
     * @param context
     * @return
     */
    @Override
    protected int getColor(Context context) {
        int color;
        if (this.isEnabled()) {
            color = ColorHolder.color(getTextColor(), context,
                    com.mikepenz.materialdrawer.R.attr.material_drawer_secondary_text,
                    com.mikepenz.materialdrawer.R.color.material_drawer_secondary_text);
        } else {
            color = ColorHolder.color(getDisabledTextColor(),
                    context, com.mikepenz.materialdrawer.R.attr.material_drawer_hint_text,
                    com.mikepenz.materialdrawer.R.color.material_drawer_hint_text);
        }
        return color;
    }
}

