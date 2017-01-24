package io.petros.posts.datastore;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import io.petros.posts.PreconfiguredRobolectricTestRunner;
import io.petros.posts.RobolectricGeneralTestHelper;
import io.petros.posts.model.User;
import io.realm.Realm;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * This test is ignored until PowerMock is introduced, which will mock all the Realm objects.
 */
@Ignore("java.lang.NullPointerException at io.realm.BaseRealm.beginTransaction")
@RunWith(PreconfiguredRobolectricTestRunner.class)
public class DatastoreUpdateActionsTest extends RobolectricGeneralTestHelper {

    private DatastoreUpdateActions datastoreUpdateActions;
    @Mock private Realm realmMock;
    @Mock private User realmUserMock;

    @Before
    public void setUp() {
        setUpMocks();
        setUpDatastoreActions();
    }

    private void setUpDatastoreActions() {
        datastoreUpdateActions = new DatastoreUpdateActions(realmMock);
    }

    @Test
    public void addUserTest() {
        final boolean isSuccessful = datastoreUpdateActions.user(realmUserMock, user);

        verify(realmMock, times(1)).beginTransaction();
        verify(realmUserMock, times(1)).setName(user.getName());
        verify(realmUserMock, times(1)).setUsername(user.getUsername());
        verify(realmUserMock, times(1)).setEmail(user.getEmail());
        verifyNoMoreInteractions(realmUserMock);
        verify(realmMock, times(1)).commitTransaction();
        verifyNoMoreInteractions(realmMock);
        assertThat(isSuccessful).isTrue();
    }

}
