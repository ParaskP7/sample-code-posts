package io.petros.posts.datastore;

import javax.annotation.Nullable;

import io.petros.posts.model.User;
import io.realm.Realm;

public class DatastoreGetActions implements GetActions {

    private final Realm realm;

    public DatastoreGetActions(final Realm realm) {
        this.realm = realm;
    }

    @Nullable
    @Override
    public User user(final Integer userId) {
        return realm.where(User.class)
                .equalTo(User.ID, userId)
                .findFirst();
    }

}
