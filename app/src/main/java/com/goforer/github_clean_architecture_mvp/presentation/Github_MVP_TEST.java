package com.goforer.github_clean_architecture_mvp.presentation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.Log;
import android.widget.ImageView;

import com.goforer.github_clean_architecture_mvp.R;
import com.goforer.github_clean_architecture_mvp.presentation.exception.ExceptionHandler;
import com.goforer.github_clean_architecture_mvp.presentation.view.view.drawer.loader.AbstractSlidingDrawerImageLoader;
import com.goforer.github_clean_architecture_mvp.presentation.view.view.drawer.loader.SlidingDrawerImageLoader;
import com.bumptech.glide.Glide;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerUIUtils;


public class Github_MVP_TEST extends MultiDexApplication {
    private static final String TAG = "Zimgo";

    @SuppressLint("StaticFieldLeak")
    public static Context mContext;
    public static Resources mResources;


    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();
        mResources = getResources();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Thread.UncaughtExceptionHandler exceptionHandler =
                        Thread.getDefaultUncaughtExceptionHandler();
                try {
                    exceptionHandler = new ExceptionHandler(Github_MVP_TEST.this, exceptionHandler,
                            new ExceptionHandler.OnFindCrashLogListener() {

                                @Override
                                public void onFindCrashLog(String log) {
                                    Log.e("onFindCrashLog", log);
                                }

                                @Override
                                public void onCaughtCrash(Throwable throwable) {
                                    Log.e(TAG, "Ooooooops Crashed!!");
                                }
                            });

                    Thread.setDefaultUncaughtExceptionHandler(exceptionHandler);
                } catch( NullPointerException e ) {
                    e.printStackTrace();
                }
            }
        }).start();

        //initialize and create the image loader logic
        DrawerImageLoader.init(new AbstractDrawerImageLoader() {
            @Override
            public void set(ImageView imageView, Uri uri, Drawable placeholder) {
                Glide.with(imageView.getContext()).load(uri).placeholder(placeholder).into(imageView);
            }

            @Override
            public void cancel(ImageView imageView) {
                Glide.clear(imageView);
            }

            @Override
            public Drawable placeholder(Context ctx, String tag) {
                //define different placeholders for different imageView targets
                //default tags are accessible via the DrawerImageLoader.Tags
                //custom ones can be checked via string. see the CustomUrlBasePrimaryDrawerItem LINE 111
                if (DrawerImageLoader.Tags.PROFILE.name().equals(tag)) {
                    return DrawerUIUtils.getPlaceHolder(ctx);
                } else if (DrawerImageLoader.Tags.ACCOUNT_HEADER.name().equals(tag)) {
                    return new IconicsDrawable(ctx).iconText(" ").backgroundColorRes(com.mikepenz.materialdrawer.R.color.primary).sizeDp(56);
                } else if ("customUrlItem".equals(tag)) {
                    return new IconicsDrawable(ctx).iconText(" ").backgroundColorRes(R.color.md_red_500).sizeDp(56);
                }

                //we use the default one for
                //DrawerImageLoader.Tags.PROFILE_DRAWER_ITEM.name()

                return super.placeholder(ctx, tag);
            }
        });

        SlidingDrawerImageLoader.init(new AbstractSlidingDrawerImageLoader() {
            @Override
            public void set(ImageView imageView, Uri uri, Drawable placeholder) {
                Glide.with(imageView.getContext()).load(uri).placeholder(placeholder).into(imageView);
            }

            @Override
            public void cancel(ImageView imageView) {
                Glide.clear(imageView);
            }

            @Override
            public Drawable placeholder(Context ctx, String tag) {
                //define different placeholders for different imageView targets
                //default tags are accessible via the DrawerImageLoader.Tags
                //custom ones can be checked via string. see the CustomUrlBasePrimaryDrawerItem LINE 111
                return super.placeholder(ctx, tag);
            }
        });

    }

    public static void closeApplication() {
        System.exit(0);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(Github_MVP_TEST.this);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

}

