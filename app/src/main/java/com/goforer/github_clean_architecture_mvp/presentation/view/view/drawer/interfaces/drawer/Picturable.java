package com.goforer.github_clean_architecture_mvp.presentation.view.view.drawer.interfaces.drawer;

import android.graphics.drawable.Drawable;

import com.mikepenz.iconics.typeface.IIcon;
import com.mikepenz.materialdrawer.holder.ImageHolder;

public interface Picturable<T> {
    T withPicture(Drawable picture);

    T withPicture(IIcon iicon);

    T withPicture(ImageHolder picture);

    ImageHolder getPicture();
}
