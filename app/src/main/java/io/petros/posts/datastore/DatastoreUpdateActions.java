package io.petros.posts.datastore;

import io.petros.posts.model.User;
import io.realm.Realm;

public class DatastoreUpdateActions implements UpdateActions {

    private final Realm realm;

    public DatastoreUpdateActions(final Realm realm) {
        this.realm = realm;
    }

    @Override
    public boolean user(final User existingRealUser, final User user) {
        realm.beginTransaction();
        existingRealUser.setName(user.getName());
        existingRealUser.setUsername(user.getUsername());
        existingRealUser.setEmail(user.getEmail());
        realm.commitTransaction();
        return true;
    }

}
