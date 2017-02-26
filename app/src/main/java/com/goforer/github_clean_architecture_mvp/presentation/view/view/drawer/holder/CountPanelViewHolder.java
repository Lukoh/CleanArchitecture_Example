package com.goforer.github_clean_architecture_mvp.presentation.view.view.drawer.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.goforer.github_clean_architecture_mvp.R;
import com.mikepenz.materialdrawer.model.BaseViewHolder;

public class CountPanelViewHolder extends BaseViewHolder {
    protected TextView mCount;
    protected LinearLayout arrowContainer;

    public CountPanelViewHolder(View view) {
        super(view);

        this.mCount = (TextView) view.findViewById(R.id.material_drawer_count);
        this.arrowContainer = (LinearLayout) view.findViewById(R.id.material_drawer_arrow_container);
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

    public TextView getCount() {
        return mCount;
    }

    public LinearLayout getArrowContainer() {
        return arrowContainer;
    }
}
