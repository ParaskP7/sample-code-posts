package io.petros.posts.activity.posts;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;

import io.petros.posts.PreconfiguredRobolectricTestRunner;
import io.petros.posts.R;
import io.petros.posts.RobolectricGeneralTestHelper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@Ignore("Problem with RxJava: Attempted to consume batched input events but the input event receiver has already been disposed.")
@RunWith(PreconfiguredRobolectricTestRunner.class)
public class PostsActivityTest extends RobolectricGeneralTestHelper {

    private PostsActivity postsActivity;

    @Before
    public void setUp() {
        setUpMocks();
        setUpActivity();
    }

    private void setUpActivity() {
        postsActivity = Robolectric.setupActivity(PostsActivity.class);
    }

    @Test
    public void activityNotNullTest() {
        assertThat(postsActivity).isNotNull();
    }

    @Test
    public void activityInjectsNotNullTest() {
        assertThat(postsActivity.application).isNotNull();
    }

    @Test
    public void activityViewsNotNullTest() {
        assertThat(postsActivity.coordinatorLayout).isNotNull();
        assertThat(postsActivity.toolbar).isNotNull();
    }

    // ON CLICK // **********************************************************************************************************

    @Test
    public void floatingActionButtonClickedTest() {
        postsActivity.application = applicationMock;

        postsActivity.floatingActionButtonClicked();

        verify(snackbarActionsMock, times(1)).info(R.string.snackbar__text__pull_down_to_refresh);
        verifyNoMoreInteractions(snackbarActionsMock);
    }

}
