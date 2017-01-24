package io.petros.posts.activity.posts;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;

import javax.annotation.Nullable;
import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.petros.posts.R;
import io.petros.posts.activity.AbstractAppActivity;
import io.petros.posts.app.PostsApplication;

public class PostsActivity extends AbstractAppActivity {

    @Inject PostsApplication application;

    @BindView(R.id.activity_posts__coordinator_layout) CoordinatorLayout coordinatorLayout;
    @BindView(R.id.activity_posts__toolbar) Toolbar toolbar;

    // LIFECYCLE // *********************************************************************************************************

    @Override
    @SuppressFBWarnings("NP_PARAMETER_MUST_BE_NONNULL_BUT_MARKED_AS_NULLABLE")
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);
        setActivityDependencies();
        setSupportActionBar(toolbar);
        setSnackbar(coordinatorLayout);
        setFragment(R.id.activity_posts__fragment);
    }

    protected void setActivityDependencies() {
        PostsApplication.getApplicationComponent().inject(this);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setSnackbar(coordinatorLayout);
    }

    // ON CLICK // **********************************************************************************************************

    @OnClick(R.id.activity_posts__fab)
    public void floatingActionButtonClicked() {
        application.snackbar().info(R.string.snackbar__text__pull_down_to_refresh);
    }

}
