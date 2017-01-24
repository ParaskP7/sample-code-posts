package io.petros.posts.activity.posts.view.recycler;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.squareup.picasso.Picasso;

import javax.annotation.Nullable;
import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import io.petros.posts.R;
import io.petros.posts.app.PostsApplication;
import io.petros.posts.datastore.Datastore;
import io.petros.posts.model.Post;
import io.petros.posts.model.User;
import io.petros.posts.util.AvatarUtilities;
import timber.log.Timber;

public class PostViewHolder extends RecyclerView.ViewHolder {

    @Inject Datastore datastore;

    @BindView(R.id.item_view_post__avatar_circle_image_view) CircleImageView circleImageView;
    @BindView(R.id.item_view_post__title_text_view) AppCompatTextView titleTextView;

    PostViewHolder(final View itemView) {
        super(itemView);
        PostsApplication.getApplicationComponent().inject(this);
        ButterKnife.bind(this, itemView);
    }

    void bind(final Post post, final OnViewClickListener onViewClickListener) {
        setOnPostClickListener(post, onViewClickListener);
        setViews(post);
    }

    private void setOnPostClickListener(final Post post, final OnViewClickListener onViewClickListener) {
        itemView.setOnClickListener(view -> onViewClickListener.onViewClick(circleImageView, post));
    }

    private void setViews(final Post post) {
        setCircleImageView(post);
        setTitleTextView(post);
    }

    private void setCircleImageView(final Post post) {
        @Nullable final User user = datastore.get().user(post.getUserId());
        if (user != null) {
            Picasso.with(itemView.getContext())
                    .load(AvatarUtilities.getUri(user.getEmail()))
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(circleImageView);
        } else {
            Timber.e("Cannot get a user for this post!");
        }
    }

    private void setTitleTextView(final Post post) {
        titleTextView.setText(post.getTitle());
    }

}
