package io.petros.posts.activity.post.view;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.squareup.picasso.Picasso;

import javax.annotation.Nullable;
import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.petros.posts.R;
import io.petros.posts.activity.AbstractAppActivity;
import io.petros.posts.activity.post.presenter.PostPresenter;
import io.petros.posts.app.PostsApplication;
import io.petros.posts.util.AvatarUtilities;

public class PostActivity extends AbstractAppActivity implements PostView {

    @Inject PostsApplication application;

    @Inject PostPresenter postPresenter;

    @BindView(R.id.activity_post__coordinator_layout) CoordinatorLayout coordinatorLayout;
    @BindView(R.id.activity_post__toolbar) Toolbar toolbar;

    @BindView(R.id.activity_post__avatar_circle_image_view) CircleImageView circleImageView;
    @BindView(R.id.activity_post__name_text_view) AppCompatTextView nameTextView;
    @BindView(R.id.activity_post__username_text_view) AppCompatTextView usernameTextView;
    @BindView(R.id.activity_post__title_text_view) AppCompatTextView titleTextView;
    @BindView(R.id.activity_post__body_text_view) AppCompatTextView bodyTextView;
    @BindView(R.id.activity_post__number_of_comments_text_view) AppCompatTextView numberOfCommentsTextView;

    // LIFECYCLE // *********************************************************************************************************

    @Override
    @SuppressFBWarnings("NP_PARAMETER_MUST_BE_NONNULL_BUT_MARKED_AS_NULLABLE")
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        setActivityDependencies();
        setToolbar(toolbar);
        setSnackbar(coordinatorLayout);
        setPresenter();
        loadPostUserAndPost();
    }

    protected void setActivityDependencies() {
        PostsApplication.getApplicationComponent().inject(this);
        ButterKnife.bind(this);
    }

    private void setPresenter() {
        postPresenter.attachView(this);
    }

    @Override
    public void loadPostUserAndPost() {
        postPresenter.loadPostUserAndPost(getIntent().getExtras());
    }

    @Override
    protected void onResume() {
        super.onResume();
        setSnackbar(coordinatorLayout);
    }

    // VIEW // **************************************************************************************************************

    @Override
    public void loadPostUser(final String userEmail, final String userName, final String userUsername) {
        Picasso.with(getApplicationContext())
                .load(AvatarUtilities.getUri(userEmail))
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(circleImageView);
        nameTextView.setText(userName);
        usernameTextView.setText(userUsername);
    }

    @Override
    public void notifyUserAboutUserUnavailability() {
        application.snackbar().warn(R.string.snackbar__text__cannot_retrieve_user);
    }

    @Override
    public void notifyUserAboutInternetUnavailability() {
        application.snackbar().warn(R.string.snackbar__text__internet_not_available);
        numberOfCommentsTextView.setText(R.string.activity__not_applicable);
    }

    @Override
    public void loadPost(final String postTitle, final String postBody, final String numberOfComments) {
        titleTextView.setText(postTitle);
        bodyTextView.setText(postBody);
        numberOfCommentsTextView.setText(numberOfComments);
    }

    @Override
    public void notifyUserAboutPostUnavailability() {
        application.snackbar().warn(R.string.snackbar__text__cannot_retrieve_post);
    }

    @Override
    public void showError() {
        application.snackbar().warn(R.string.snackbar__text__error_getting_comments);
    }

    // MENU ITEMS // ********************************************************************************************************

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        final int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
