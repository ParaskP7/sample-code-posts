package io.petros.posts.activity.post.view;

import android.content.Intent;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.fakes.RoboMenuItem;

import io.petros.posts.PreconfiguredRobolectricTestRunner;
import io.petros.posts.RobolectricGeneralTestHelper;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This test is ignored until a test Dagger graph is introduced, which will mock all the @Inject objects.
 */
@Ignore("Problem with Realm: The activity cannot start.")
@RunWith(PreconfiguredRobolectricTestRunner.class)
public class PostActivityTest extends RobolectricGeneralTestHelper {

    private PostActivity postActivity;
    private RoboMenuItem homeMenuItem;

    @Before
    public void setUp() {
        setUpMocks();
        setUpActivity();
    }

    private void setUpActivity() {
        final Intent intent = new Intent(context, PostActivity.class);
        intent.putExtras(getExtras(post));
        postActivity = Robolectric.buildActivity(PostActivity.class).withIntent(intent).create().get();
        setUpMenuItems();
    }

    private void setUpMenuItems() {
        homeMenuItem = new RoboMenuItem(android.R.id.home);
    }

    @Test
    public void activityNotNullTest() {
        assertThat(postActivity).isNotNull();
    }

    @Test
    public void activityInjectsNotNullTest() {
        assertThat(postActivity.application).isNotNull();
        assertThat(postActivity.postPresenter).isNotNull();
    }

    @Test
    public void activityStringsNotNullTest() {
        assertThat(postActivity.circleImageView).isNotNull();
        assertThat(postActivity.nameTextView).isNotNull();
        assertThat(postActivity.usernameTextView).isNotNull();
        assertThat(postActivity.titleTextView).isNotNull();
        assertThat(postActivity.bodyTextView).isNotNull();
        assertThat(postActivity.numberOfCommentsTextView).isNotNull();
    }

    @Test
    public void activityViewsNotNullTest() {
        assertThat(postActivity.coordinatorLayout).isNotNull();
        assertThat(postActivity.toolbar).isNotNull();
    }

    @Test
    public void activityMenuItemsNotNullTest() {
        assertThat(homeMenuItem).isNotNull();
    }

    // ON CLICK // **********************************************************************************************************

    @Test
    public void onOptionsItemSelectedTest() {
        final boolean consumeHere = postActivity.onOptionsItemSelected(homeMenuItem);

        assertThat(consumeHere).isTrue();
    }

}
