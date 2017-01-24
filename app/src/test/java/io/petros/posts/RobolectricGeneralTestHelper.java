package io.petros.posts;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;

import org.mockito.Mock;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.shadows.ShadowApplication;

import io.petros.posts.app.PostsApplication;
import io.petros.posts.app.actions.SnackbarActions;
import io.petros.posts.model.Post;

import static org.mockito.Mockito.when;

public class RobolectricGeneralTestHelper extends GeneralTestHelper {

    // Application specific fields.
    protected final Context context = getTestContext();
    protected final PostsApplication application = getTestApplication();

    // System specific fields.
    @Mock protected ConnectivityManager connectivityManagerMock;

    // Activity specific fields.
    @Mock protected PostsApplication applicationMock;
    @Mock protected SnackbarActions snackbarActionsMock;

    // ROBOLECTRIC // *******************************************************************************************************

    private Context getTestContext() {
        return getShadowApplication().getApplicationContext();
    }

    private ShadowApplication getShadowApplication() {
        return ShadowApplication.getInstance();
    }

    private PostsApplication getTestApplication() {
        return (PostsApplication) RuntimeEnvironment.application;
    }

    // MOCKS // *************************************************************************************************************

    protected void setUpMocks() {
        super.setUpMocks();
        setUpApplicationMocks();
    }

    private void setUpApplicationMocks() {
        when(applicationMock.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(connectivityManagerMock);
        when(applicationMock.snackbar()).thenReturn(snackbarActionsMock);
    }

    // POST // **************************************************************************************************************

    protected Bundle getExtras(final Post post) {
        final Bundle extras = new Bundle();
        extras.putInt(Post.USER_ID, post.getUserId());
        extras.putInt(Post.ID, post.getId());
        extras.putString(Post.TITLE, post.getTitle());
        extras.putString(Post.BODY, post.getBody());
        return extras;
    }

}
