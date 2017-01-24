package io.petros.posts.datastore;

import javax.annotation.Nullable;

import io.petros.posts.model.User;

public interface GetActions {

    @Nullable
    User user(final Integer userId);

}
