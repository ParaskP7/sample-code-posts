package io.petros.posts.service.detector;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import javax.annotation.Nullable;

import io.petros.posts.app.PostsApplication;
import timber.log.Timber;

public class InternetAvailabilityDetector implements AvailabilityDetector {

    private final ConnectivityManager connectivityManager;

    public InternetAvailabilityDetector(final PostsApplication application) {
        this.connectivityManager = (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Override
    public boolean isAvailable() {
        @Nullable final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                Timber.v("Internet connection is available; Connected to WiFi.");
                return true;
            } else if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                Timber.v("Internet connection is available; Connected to the mobile provider's data plan.");
                return true;
            } else {
                Timber.v("Internet connection is available. [Network Type: %d]", activeNetworkInfo.getType());
                return true;
            }
        } else {
            Timber.d("Internet connection is not available at the moment.");
            return false;
        }
    }

}
