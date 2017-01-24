package io.petros.posts.datastore;

import org.junit.Before;
import org.junit.Test;

import io.petros.posts.GeneralTestHelper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class DatastoreSaveActionsTest extends GeneralTestHelper {

    private DatastoreSaveActions datastoreSaveActions;

    @Before
    public void setUp() {
        setUpMocks();
        setUpDatastoreActions();
    }

    private void setUpDatastoreActions() {
        datastoreSaveActions = new DatastoreSaveActions(datastoreAddActionsMock, datastoreGetActionsMock,
                datastoreUpdateActionsMock);
    }

    @Test
    public void givenTheUserDoesNotExist_WhenItIsSaved_ThenANewUserIsAdded() {
        when(datastoreGetActionsMock.user(user.getId())).thenReturn(null);

        final boolean isSuccessful = datastoreSaveActions.users(users);

        verify(datastoreGetActionsMock, times(1)).user(user.getId());
        verifyNoMoreInteractions(datastoreGetActionsMock);
        verify(datastoreAddActionsMock, times(1)).user(user);
        verifyNoMoreInteractions(datastoreAddActionsMock);
        assertThat(isSuccessful).isTrue();
    }

    @Test
    public void givenTheUserExists_WhenItIsSaved_ThenTheExistingUserIsUpdated() {
        when(datastoreGetActionsMock.user(user.getId())).thenReturn(user);

        final boolean isSuccessful = datastoreSaveActions.users(users);

        verify(datastoreGetActionsMock, times(1)).user(user.getId());
        verifyNoMoreInteractions(datastoreGetActionsMock);
        verify(datastoreUpdateActionsMock, times(1)).user(user, user);
        verifyNoMoreInteractions(datastoreUpdateActionsMock);
        assertThat(isSuccessful).isTrue();
    }

}
