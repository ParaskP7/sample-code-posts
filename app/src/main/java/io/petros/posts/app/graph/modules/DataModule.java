package io.petros.posts.app.graph.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.petros.posts.app.PostsApplication;
import io.petros.posts.datastore.Datastore;
import io.petros.posts.datastore.DatastoreAddActions;
import io.petros.posts.datastore.DatastoreGetActions;
import io.petros.posts.datastore.DatastoreSaveActions;
import io.petros.posts.datastore.DatastoreUpdateActions;
import io.realm.Realm;

@Module
public class DataModule {

    @Provides
    @Singleton
    Realm provideRealm(final PostsApplication application) {
        return application.getDefaultRealmInstance();
    }

    @Provides
    @Singleton
    Datastore provideDatastore(final DatastoreSaveActions datastoreSaveActions, final DatastoreAddActions datastoreAddActions,
                               final DatastoreGetActions datastoreGetActions, final DatastoreUpdateActions datastoreUpdateActions) {
        return new Datastore(datastoreSaveActions, datastoreAddActions, datastoreGetActions, datastoreUpdateActions);
    }

    @Provides
    @Singleton
    DatastoreSaveActions provideDatastoreSaveActions(final DatastoreAddActions datastoreAddActions,
                                                     final DatastoreGetActions datastoreGetActions,
                                                     final DatastoreUpdateActions datastoreUpdateActions) {
        return new DatastoreSaveActions(datastoreAddActions, datastoreGetActions, datastoreUpdateActions);
    }

    @Provides
    @Singleton
    DatastoreAddActions provideDatastoreAddActions(final Realm realm) {
        return new DatastoreAddActions(realm);
    }

    @Provides
    @Singleton
    DatastoreGetActions provideDatastoreGetActions(final Realm realm) {
        return new DatastoreGetActions(realm);
    }

    @Provides
    @Singleton
    DatastoreUpdateActions provideDatastoreUpdateActions(final Realm realm) {
        return new DatastoreUpdateActions(realm);
    }

}
