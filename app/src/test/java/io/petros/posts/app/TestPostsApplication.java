package io.petros.posts.app;

import org.mockito.Mockito;

import io.realm.Realm;

public class TestPostsApplication extends PostsApplication {

    @Override
    protected void initRealm() {
        // Do nothing because during testing databaseRealm.setup() throws an java.lang.UnsatisfiedLinkError error.
    }

    @Override
    public Realm getDefaultRealmInstance() {
        return Mockito.mock(Realm.class); // During testing return a mocked Realm instance instead.
    }

}
