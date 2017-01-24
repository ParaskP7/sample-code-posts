package io.petros.posts.app.actions;

import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import javax.annotation.Nullable;

import io.petros.posts.R;
import io.petros.posts.app.PostsApplication;
import io.petros.posts.util.DeprecationUtilities;
import timber.log.Timber;

public class AppSnackbarActions implements SnackbarActions {

    private final PostsApplication application;
    private final String defaultActionText;

    @Nullable private CoordinatorLayout coordinatorLayout;

    public AppSnackbarActions(final PostsApplication application) {
        this.application = application;
        this.defaultActionText = application.getString(R.string.snackbar__action_text__ok);
    }

    // COORDINATION LAYOUT // ***********************************************************************************************

    /**
     * This is so that the snackbar and fab per activity work in coordination. When the snackbar appear the fab should move
     * up and when the snackbar disappears the fab should move down to its original position.
     */
    @Override
    public void setCoordinatorLayout(final CoordinatorLayout coordinatorLayout) {
        this.coordinatorLayout = coordinatorLayout;
    }

    // SNACKBAR // **********************************************************************************************************

    @SuppressWarnings("checkstyle:whitespacearound")
    public void info(final int textResId) {
        final String text = application.getString(textResId);
        if (coordinatorLayout != null) {
            final Snackbar snackbar = Snackbar.make(coordinatorLayout, text, Snackbar.LENGTH_LONG) // @formatter:off
                    .setAction(defaultActionText, v -> {}); // @formatter:on
            setColors(snackbar, 0, R.color.white, R.color.color_accent);
            snackbar.show();
        } else {
            Toast.makeText(application, text, Toast.LENGTH_LONG).show();
        }
    }

    private void setColors(final Snackbar snackbar, final int backgroundColorId, final int textColorId, final int actionTextColorId) {
        changeSnackbarBackgroundColor(snackbar, backgroundColorId);
        changeSnackbarTextColor(snackbar, textColorId);
        changeSnackbarActionTextColor(snackbar, actionTextColorId);
    }

    private void changeSnackbarBackgroundColor(final Snackbar snackbar, final int backgroundColorId) {
        final View snackbarView = snackbar.getView();
        if (backgroundColorId != 0) {
            snackbarView.setBackgroundColor(ContextCompat.getColor(application, backgroundColorId));
        } else { // This is the snackbar's default background color.
            DeprecationUtilities.setBackground(snackbarView, R.drawable.snackbar__design_snackbar_background);
        }
    }

    private void changeSnackbarTextColor(final Snackbar snackbar, final int textColorId) {
        final View snackbarView = snackbar.getView();
        final TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        if (textColorId != 0) {
            textView.setTextColor(ContextCompat.getColor(application, textColorId));
        } else { // This is the snackbar's default text color.
            textView.setTextColor(Color.WHITE);
        }
    }

    private void changeSnackbarActionTextColor(final Snackbar snackbar, final int actionTextColorId) {
        if (actionTextColorId != 0) {
            snackbar.setActionTextColor(ContextCompat.getColor(application, actionTextColorId));
        } else { // This is the snackbar's default action text color.
            snackbar.setActionTextColor(Color.RED);
        }
    }

    @Override
    @SuppressWarnings("checkstyle:whitespacearound")
    public void warn(final int textResId) {
        final String text = application.getString(textResId);
        Timber.w(text);
        if (coordinatorLayout != null) {
            final Snackbar snackbar = Snackbar.make(coordinatorLayout, text, Snackbar.LENGTH_INDEFINITE) // @formatter:off
                    .setAction(defaultActionText, v -> {}); // @formatter:on
            setColors(snackbar, R.color.yellow_material_500, R.color.black, R.color.black);
            snackbar.show();
        } else {
            Toast.makeText(application, text, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    @SuppressWarnings("checkstyle:whitespacearound")
    public void error(final int textResId) {
        final String text = application.getString(textResId);
        Timber.e(text);
        if (coordinatorLayout != null) {
            final Snackbar snackbar = Snackbar.make(coordinatorLayout, text, Snackbar.LENGTH_INDEFINITE) // @formatter:off
                    .setAction(defaultActionText, v-> {}); // @formatter:on
            setColors(snackbar, R.color.red_material_500, R.color.black, R.color.black);
            snackbar.show();
        } else {
            Toast.makeText(application, text, Toast.LENGTH_LONG).show();
        }
    }

}
