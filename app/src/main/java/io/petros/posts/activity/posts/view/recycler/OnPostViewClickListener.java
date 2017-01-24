package io.petros.posts.activity.posts.view.recycler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;

import butterknife.BindString;
import butterknife.ButterKnife;
import io.petros.posts.R;
import io.petros.posts.activity.post.view.PostActivity;
import io.petros.posts.model.Post;
import io.petros.posts.util.VersionUtilities;

public class OnPostViewClickListener implements OnViewClickListener {

    private final Activity activity;

    @BindString(R.string.activity__avatar_shared_element) String avatarSharedElement;

    public OnPostViewClickListener(final Activity activity) {
        this.activity = activity;
        ButterKnife.bind(this, activity);
    }

    @Override
    public void onViewClick(final View sharedElementView, final Post post) {
        final Intent intent = new Intent(activity.getApplicationContext(), PostActivity.class);
        intent.putExtras(getExtras(post));
        final ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat
                .makeSceneTransitionAnimation(activity, sharedElementView, avatarSharedElement);
        VersionUtilities.startActivity(activity, intent, activityOptionsCompat.toBundle());
    }

    private Bundle getExtras(final Post post) {
        final Bundle extras = new Bundle();
        extras.putInt(Post.USER_ID, post.getUserId());
        extras.putInt(Post.ID, post.getId());
        extras.putString(Post.TITLE, post.getTitle());
        extras.putString(Post.BODY, post.getBody());
        return extras;
    }

}
