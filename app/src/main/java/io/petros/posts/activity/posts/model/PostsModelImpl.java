package io.petros.posts.activity.posts.model;

import java.util.List;

import io.petros.posts.datastore.Datastore;
import io.petros.posts.model.User;

public class PostsModelImpl implements PostsModel {

    private final Datastore datastore;

    public PostsModelImpl(final Datastore datastore) {
        this.datastore = datastore;
    }

    @Override
    public boolean saveUsers(final List<User> users) {
        return datastore.save().users(users);
    }

}
