package com.goforer.base.presentation.view.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.support.v7.widget.AppCompatImageView;

import com.goforer.clean_architecture.R;

@SuppressWarnings("unused")
public class AnimationImageView extends AppCompatImageView {

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
