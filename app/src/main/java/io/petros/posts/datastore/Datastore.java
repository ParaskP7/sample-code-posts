package io.petros.posts.datastore;

public class Datastore {

    private final DatastoreSaveActions datastoreSaveActions;
    private final DatastoreAddActions datastoreAddActions;
    private final DatastoreGetActions datastoreGetActions;
    private final DatastoreUpdateActions datastoreUpdateActions;

    public Datastore(final DatastoreSaveActions datastoreSaveActions, final DatastoreAddActions datastoreAddActions,
                     final DatastoreGetActions datastoreGetActions, final DatastoreUpdateActions datastoreUpdateActions) {
        this.datastoreSaveActions = datastoreSaveActions;
        this.datastoreAddActions = datastoreAddActions;
        this.datastoreGetActions = datastoreGetActions;
        this.datastoreUpdateActions = datastoreUpdateActions;
    }

    public SaveActions save() {
        return datastoreSaveActions;
    }

    public AddActions add() {
        return datastoreAddActions;
    }

    public GetActions get() {
        return datastoreGetActions;
    }

    public UpdateActions update() {
        return datastoreUpdateActions;
    }

}
