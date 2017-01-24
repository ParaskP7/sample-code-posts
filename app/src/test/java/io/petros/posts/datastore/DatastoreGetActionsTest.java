package io.petros.posts.datastore;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import io.petros.posts.GeneralTestHelper;
import io.petros.posts.model.User;
import io.realm.Realm;
import io.realm.RealmQuery;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class DatastoreGetActionsTest extends GeneralTestHelper {

    private DatastoreGetActions datastoreGetActions;
    @Mock private Realm realmMock;
    @Mock private RealmQuery realmQueryMock;

    @Before
    public void setUp() {
        setUpMocks();
        setUpDatastoreActions();
    }

    private void setUpDatastoreActions() {
        datastoreGetActions = new DatastoreGetActions(realmMock);
    }

    @Test
    public void addUserTest() {
        when(realmMock.where(User.class)).thenReturn(realmQueryMock);
        when(realmQueryMock.equalTo(User.ID, user.getId())).thenReturn(realmQueryMock);
        when(realmQueryMock.findFirst()).thenReturn(user);

        final User persistedUser = datastoreGetActions.user(user.getId());

        verify(realmMock, times(1)).where(User.class);
        verifyNoMoreInteractions(realmMock);
        verify(realmQueryMock, times(1)).equalTo(User.ID, user.getId());
        verify(realmQueryMock, times(1)).findFirst();
        verifyNoMoreInteractions(realmQueryMock);
        assertThat(persistedUser).isEqualTo(user);
    }

}
