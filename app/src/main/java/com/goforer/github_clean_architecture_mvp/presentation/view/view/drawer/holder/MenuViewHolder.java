package com.goforer.github_clean_architecture_mvp.presentation.view.view.drawer.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.goforer.github_clean_architecture_mvp.R;

public class MenuViewHolder extends RecyclerView.ViewHolder {
    protected View view;
    protected ImageView icon;
    protected TextView menu;
    protected TextView menu_sub1;
    protected TextView menu_sub2;
    protected TextView description;

    public MenuViewHolder(View view) {
        super(view);

        this.view = view;
        this.icon = (ImageView) view.findViewById(R.id.material_drawer_icon);
        this.menu = (TextView) view.findViewById(R.id.material_drawer_menu);
        this.menu_sub1 = (TextView) view.findViewById(R.id.material_drawer_menu_sub1);
        this.menu_sub2 = (TextView) view.findViewById(R.id.material_drawer_menu_sub2);
        this.description = (TextView) view.findViewById(R.id.material_drawer_menu_description);
    }

    public View getView() {
        return view;
    }

    public ImageView getIcon() {
        return icon;
    }

    public TextView getMenu() {
        return menu;
    }

    public TextView getMenuSub1() {
        return menu_sub1;
    }

    public TextView getMenuSub2() {
        return menu_sub2;
    }

    public TextView getDescription() {
        return description;
    }
}
