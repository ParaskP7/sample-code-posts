package io.petros.posts.datastore;

import java.util.List;

import io.petros.posts.model.User;

public interface SaveActions {

    boolean users(final List<User> users);

}
