package io.petros.posts.util;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.View;

public final class DeprecationUtilities {

    private DeprecationUtilities() {
        throw new AssertionError();
    }

    public static void setBackground(final View view, final int drawableId) {
        final Drawable drawable = ContextCompat.getDrawable(view.getContext(), drawableId);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

}
