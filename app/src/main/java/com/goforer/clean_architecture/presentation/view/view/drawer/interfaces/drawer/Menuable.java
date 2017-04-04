package com.goforer.clean_architecture.presentation.view.view.drawer.interfaces.drawer;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import com.mikepenz.fastadapter.IIdentifyable;
import com.mikepenz.iconics.typeface.IIcon;
import com.mikepenz.materialdrawer.holder.ImageHolder;
import com.mikepenz.materialdrawer.holder.StringHolder;

@SuppressWarnings("unused")
public interface Menuable<T> extends IIdentifyable<T> {
    T withMenu(String menu);

    T withMenu(@StringRes int menuRes);

    T withMenu(StringHolder menu);

    StringHolder getMenu();

    T withMenuSub1(String menuSub1);

    T withMenuSub1(@StringRes int menuSub1Res);

    T withMenuSub1(StringHolder menuSub1);

    StringHolder getMenuSub1();

    T withMenuSub2(String menuSub2);

    T withMenuSub2(@StringRes int menuSub2Res);

    T withMenuSub2(StringHolder menuSub2);

    StringHolder getMenuSub2();

    T withDescription(String description);

    T withDescription(@StringRes int descriptionRes);

    StringHolder getDescription();

    T withIcon(Drawable icon);

    T withIcon(Bitmap bitmap);

    T withIcon(@DrawableRes int iconRes);

    T withIcon(String url);

    T withIcon(Uri uri);

    T withIcon(IIcon icon);

    ImageHolder getIcon();

    T withSelectable(boolean selectable);

    boolean isSelectable();
}

