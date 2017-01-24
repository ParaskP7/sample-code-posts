package io.petros.posts.activity.posts.view.recycler;

import java.util.List;

import io.petros.posts.model.Post;

public interface RecyclerViewAdapter {

    void reloadAdapter(final List<Post> posts);

    Post getItem(final int position);

}
