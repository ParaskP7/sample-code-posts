package io.petros.posts.activity.posts.model;

import java.util.List;

import io.petros.posts.model.User;

public interface PostsModel {

    boolean saveUsers(final List<User> users);

}
