package com.goforer.base.presentation.view.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.goforer.github_clean_architecture_mvp.R;

public class AnimationImageView extends ImageView {

    AnimationDrawable mDrawable;

    public AnimationImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        startAnimation();
    }

    private void startAnimation() {
        setBackgroundResource(R.drawable.loading_on_list);
        mDrawable = (AnimationDrawable)getBackground();
        mDrawable.start();
    }
}
