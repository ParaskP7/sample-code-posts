package io.petros.posts.activity.posts.view.recycler;

import android.view.View;

import io.petros.posts.model.Post;

public interface OnViewClickListener {

    void onViewClick(final View sharedElementView, final Post post);

}
