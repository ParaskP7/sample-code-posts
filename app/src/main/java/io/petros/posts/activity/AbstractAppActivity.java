package io.petros.posts.activity;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import javax.annotation.Nullable;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.petros.posts.app.PostsApplication;
import timber.log.Timber;

@SuppressLint("Registered")
public class AbstractAppActivity extends AppCompatActivity {

    // LIFECYCLE // *********************************************************************************************************

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.d("%s created.", getClass().getSimpleName());
    }

    @Override
    public void onRestart() {
        super.onRestart();
        Timber.d("%s restarted.", getClass().getSimpleName());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Timber.d("%s started.", getClass().getSimpleName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Timber.d("%s resumed.", getClass().getSimpleName());
    }

    @Override
    @CallSuper
    protected void onPause() {
        Timber.d("%s paused.", getClass().getSimpleName());
        super.onPause();
    }

    @Override
    protected void onStop() {
        Timber.d("%s stopped.", getClass().getSimpleName());
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Timber.d("%s destroyed.", getClass().getSimpleName());
        super.onDestroy();
    }

    // TOOLBAR // ***********************************************************************************************************

    @SuppressFBWarnings("RCN_REDUNDANT_NULLCHECK_OF_NONNULL_VALUE")
    protected void setToolbar(final Toolbar toolbar) {
        setSupportActionBar(toolbar);
        @Nullable final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        } else {
            Timber.w("Cannot get an action bar for this activity; verify that this activity has actually defined a toolbar.");
        }
    }

    // SNACKBAR // **********************************************************************************************************

    protected void setSnackbar(final CoordinatorLayout coordinatorLayout) {
        PostsApplication.snackbar(this).setCoordinatorLayout(coordinatorLayout);
    }

    // FRAGMENT // **********************************************************************************************************

    protected void setFragment(final int containerViewId) {
        final FragmentManager fragmentManager = getFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(containerViewId, new Fragment());
        fragmentTransaction.commit();
    }

}
