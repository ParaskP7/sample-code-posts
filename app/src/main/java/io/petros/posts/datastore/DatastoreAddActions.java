package io.petros.posts.datastore;

import io.petros.posts.model.User;
import io.realm.Realm;

public class DatastoreAddActions implements AddActions {

    private final Realm realm;

    public DatastoreAddActions(final Realm realm) {
        this.realm = realm;
    }

    @Override
    public boolean user(final User user) {
        realm.beginTransaction();
        final User newRealmUser = realm.createObject(User.class, user.getId());
        newRealmUser.setName(user.getName());
        newRealmUser.setUsername(user.getUsername());
        newRealmUser.setEmail(user.getEmail());
        realm.commitTransaction();
        return true;
    }

}
