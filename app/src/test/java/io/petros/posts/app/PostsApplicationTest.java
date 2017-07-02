package io.petros.posts.app;

import android.app.Activity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import io.petros.posts.PreconfiguredRobolectricTestRunner;
import io.petros.posts.RobolectricGeneralTestHelper;
import io.petros.posts.app.actions.SnackbarActions;
import io.realm.Realm;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(PreconfiguredRobolectricTestRunner.class)
public class PostsApplicationTest extends RobolectricGeneralTestHelper {

    @Mock private Activity activityMock;

    @Before
    public void setUp() {
        setUpMocks();
        setUpApplication();
    }

    private void setUpApplication() {
        when(activityMock.getApplication()).thenReturn(application);
    }

    // HELPER // ************************************************************************************************************

    @Test
    public void snackbarTestFromActivity() {
        final SnackbarActions snackbarActions = PostsApplication.snackbar(activityMock);

        assertThat(snackbarActions).isEqualTo(application.appSnackbarActions);
    }

    // GET // ***************************************************************************************************************

    @Test
    public void getDefaultRealmInstanceTest() {
        final Realm realm = application.getDefaultRealmInstance();

        assertThat(realm).isNotNull();
    }

    // ACTIONS // ***********************************************************************************************************

    @Test
    public void snackbarTestFromApplication() {
        final SnackbarActions snackbarActions = application.snackbar();

        assertThat(snackbarActions).isEqualTo(application.appSnackbarActions);
    }

}
