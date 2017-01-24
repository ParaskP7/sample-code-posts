package io.petros.posts.activity.post.model;

import javax.annotation.Nullable;

import io.petros.posts.datastore.Datastore;
import io.petros.posts.model.User;

public class PostModelImpl implements PostModel {

    private final Datastore datastore;

    public PostModelImpl(final Datastore datastore) {
        this.datastore = datastore;
    }

    @Nullable
    public User getUser(final Integer userId) {
        return datastore.get().user(userId);
    }

}
