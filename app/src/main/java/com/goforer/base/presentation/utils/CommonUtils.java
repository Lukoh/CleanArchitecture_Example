package com.goforer.base.presentation.utils;

import android.content.Context;
import android.widget.Toast;

public class CommonUtils {
    public static void showToastMessage(Context context, String text, int duration) {
        Toast.makeText(context, text, duration).show();
    }
}
