package io.petros.posts.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

public final class VersionUtilities {

    private VersionUtilities() {
        throw new AssertionError();
    }

    public static void startActivity(final Activity activity, final Intent intent, final Bundle options) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            activity.startActivity(intent, options);
        } else {
            activity.startActivity(intent);
        }
    }
}
