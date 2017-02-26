package com.goforer.github_clean_architecture_mvp.presentation.view.view.drawer.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikepenz.materialdrawer.model.BaseViewHolder;

public class IconBaseViewHolder extends BaseViewHolder {

    public IconBaseViewHolder(View view) {
        super(view);
    }

    public View getView() {
        return view;
    }

    public ImageView getIcon() {
        return icon;
    }

    public TextView getName() {
        return name;
    }

    public TextView getDescription() {
        return description;
    }
}
