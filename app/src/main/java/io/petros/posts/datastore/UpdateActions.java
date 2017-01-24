package io.petros.posts.datastore;

import io.petros.posts.model.User;

public interface UpdateActions {

    boolean user(final User existingRealUser, final User user);

}
