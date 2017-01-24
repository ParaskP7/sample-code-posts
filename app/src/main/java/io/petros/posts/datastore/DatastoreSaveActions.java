package io.petros.posts.datastore;

import java.util.List;

import javax.annotation.Nullable;

import io.petros.posts.model.User;

public class DatastoreSaveActions implements SaveActions {

    private final DatastoreAddActions datastoreAddActions;
    private final DatastoreGetActions datastoreGetActions;
    private final DatastoreUpdateActions datastoreUpdateActions;

    public DatastoreSaveActions(final DatastoreAddActions datastoreAddActions, final DatastoreGetActions datastoreGetActions,
                                final DatastoreUpdateActions datastoreUpdateActions) {
        this.datastoreAddActions = datastoreAddActions;
        this.datastoreGetActions = datastoreGetActions;
        this.datastoreUpdateActions = datastoreUpdateActions;
    }

    @Override
    public boolean users(final List<User> users) {
        for (final User user : users) {
            @Nullable final User existingRealUser = datastoreGetActions.user(user.getId());
            if (existingRealUser == null) {
                datastoreAddActions.user(user);
            } else {
                datastoreUpdateActions.user(existingRealUser, user);
            }
        }
        return true;
    }

}
