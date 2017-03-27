package com.goforer.clean_architecture.presentation.view.view.drawer.loader;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

public class SlidingDrawerImageLoader {
    public enum Tags {
        COMMENT_PICTURE,
        MENU_PICTURE,
        PRIMARY_ICON
    }

    private static SlidingDrawerImageLoader SINGLETON = null;

    private IDrawerImageLoader imageLoader;
    private boolean mHandleAllUris = false;

    private SlidingDrawerImageLoader(IDrawerImageLoader loaderImpl) {
        imageLoader = loaderImpl;
    }

    public static SlidingDrawerImageLoader init(IDrawerImageLoader loaderImpl) {
        SINGLETON = new SlidingDrawerImageLoader(loaderImpl);
        return SINGLETON;
    }

    public static SlidingDrawerImageLoader getInstance() {
        if (SINGLETON == null) {
            SINGLETON = new SlidingDrawerImageLoader(new AbstractSlidingDrawerImageLoader() {
            });
        }
        return SINGLETON;
    }

    public SlidingDrawerImageLoader withHandleAllUris(boolean handleAllUris) {
        this.mHandleAllUris = handleAllUris;
        return this;
    }

    /**
     * @param imageView
     * @param uri
     * @param tag
     * @return false if not consumed
     */
    public boolean setImage(ImageView imageView, Uri uri, String tag) {
        //if we do not handle all uris and are not http or https we keep the original behavior
        if (mHandleAllUris || "http".equals(uri.getScheme()) || "https".equals(uri.getScheme())) {
            if (imageLoader != null) {
                Drawable placeHolder = imageLoader.placeholder(imageView.getContext(), tag);
                imageLoader.set(imageView, uri, placeHolder);
            }
            return true;
        }
        return false;
    }

    public void cancelImage(ImageView imageView) {
        if (imageLoader != null) {
            imageLoader.cancel(imageView);
        }
    }

    public IDrawerImageLoader getImageLoader() {
        return imageLoader;
    }

    public void setImageLoader(IDrawerImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    public interface IDrawerImageLoader {
        void set(final ImageView imageView, Uri uri, Drawable placeholder);

        void cancel(ImageView imageView);

        Drawable placeholder(Context context);

        /**
         * @param ctx
         * @param tag current possible tags: "profile", "profileDrawerItem", "accountHeader"
         * @return
         */
        Drawable placeholder(Context ctx, String tag);
    }
}
