package com.goforer.github_clean_architecture_mvp.presentation.view.view.drawer.interfaces.drawer;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import com.mikepenz.fastadapter.IIdentifyable;
import com.mikepenz.iconics.typeface.IIcon;
import com.mikepenz.materialdrawer.holder.ImageHolder;
import com.mikepenz.materialdrawer.holder.StringHolder;

public interface Commentable<T> extends IIdentifyable<T> {
    T withCommenter(String commenter);

    T withCommenter(@StringRes int commenterRes);

    T withCommenter(StringHolder commenter);

    StringHolder getCommenter();

    T withComment(String comment);

    T withComment(@StringRes int commentRes);

    T withComment(StringHolder comment);

    StringHolder getComment();

    T withDate(String date);

    T withDate(@StringRes int dateRes);

    T withDate(StringHolder date);

    StringHolder getDate();

    T withLikeCount(String likeCount);

    T withLikeCount(StringHolder likeCount);

    T withLikeCount(@StringRes int likeCountRes);

    T withCommentId(long id);

    T withCommenterId(long id);

    StringHolder getLikeCount();

    T withPicture(Drawable picture);

    T withPicture(Bitmap bitmap);

    T withPicture(@DrawableRes int pictureRes);

    T withPicture(String url);

    T withPicture(Uri uri);

    T withPicture(IIcon icon);

    ImageHolder getPicture();

    T withSelectable(boolean selectable);

    boolean isSelectable();
}
