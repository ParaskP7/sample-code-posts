package io.petros.posts.app.actions;

import android.support.design.widget.CoordinatorLayout;

public interface SnackbarActions {

    void setCoordinatorLayout(final CoordinatorLayout coordinatorLayout);

    void info(final int textResId);

    void warn(final int textResId);

    void error(final int textResId);

}
