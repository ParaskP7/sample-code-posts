package io.petros.posts.activity.post.model;

import javax.annotation.Nullable;

import io.petros.posts.model.User;

public interface PostModel {

    @Nullable
    User getUser(final Integer userId);

}
