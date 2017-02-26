package com.goforer.github_clean_architecture_mvp.presentation.view.view.drawer.loader;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import com.mikepenz.materialdrawer.util.DrawerUIUtils;

public class AbstractSlidingDrawerImageLoader implements
        SlidingDrawerImageLoader.IDrawerImageLoader {
    @Override
    public void set(final ImageView imageView, Uri uri, Drawable placeholder) {
        //this won't do anything
        Log.i("MaterialDrawer", "you have not specified a ImageLoader implementation through " +
                "the DrawerImageLoader.init(IDrawerImageLoader) method");
    }

    @Override
    public void cancel(ImageView imageView) {
    }

    @Override
    public Drawable placeholder(Context context) {
        return DrawerUIUtils.getPlaceHolder(context);
    }

    @Override
    public Drawable placeholder(Context context, String tag) {
        return placeholder(context);
    }
}
