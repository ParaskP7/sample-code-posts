package io.petros.posts.activity.posts;

import android.support.test.espresso.intent.Intents;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.petros.posts.GeneralEspressoTestHelper;
import io.petros.posts.R;
import io.petros.posts.activity.post.view.PostActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class PostsActivityEspressoTest extends GeneralEspressoTestHelper {

    private static final boolean INITIAL_TOUCH_MODE = false;
    private static final boolean LAUNCH_ACTIVITY = true;

    private static final int FIRST_POSITION_ON_LIST = 0;
    private static final String FIRST_TEXT_ON_LIST = "sunt aut facere repellat provident occaecati excepturi optio reprehenderit";
    private static final int MIDDLE_POSITION_ON_LIST = 49;
    private static final String MIDDLE_TEXT_ON_LIST = "repellendus qui recusandae incidunt voluptates tenetur qui omnis exercitationem";
    private static final int LAST_POSITION_ON_LIST = 99;
    private static final String LAST_TEXT_ON_LIST = "at nam consequatur ea labore ea harum";

    @Rule
    @SuppressFBWarnings("URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD")
    public ActivityTestRule<PostsActivity> activityTestRule =
            new ActivityTestRule<>(PostsActivity.class, INITIAL_TOUCH_MODE, LAUNCH_ACTIVITY);

    @Before
    public void setUp() {
        Intents.init();
    }

    @After
    public void tearDown() {
        Intents.release();
    }

    // VIEW // **************************************************************************************************************

    @Test
    public void verifyActivityTitleIsShown() {
        onView(withText(R.string.posts_activity__label))
                .check(matches(isDisplayed()));
    }

    // CLICK // *************************************************************************************************************

    @Test
    public void clickFab_AndVerifySnackbarIsShown() {
        onView(withId(R.id.activity_posts__fab))
                .perform(click());
        onView(withText(R.string.snackbar__text__pull_down_to_refresh))
                .check(matches(isDisplayed()));
        onView(withText(R.string.snackbar__action_text__ok))
                .check(matches(isDisplayed()));
    }

    @Test
    public void clickFabThenSnackbarAction_AndVerifySnackbarIsHidden() {
        onView(withId(R.id.activity_posts__fab))
                .perform(click());
        onView(withText(R.string.snackbar__action_text__ok))
                .perform(click());
        onView(withText(R.string.snackbar__text__pull_down_to_refresh))
                .check(doesNotExist());
        onView(withText(R.string.snackbar__action_text__ok))
                .check(doesNotExist());
    }

    // LIST // **************************************************************************************************************

    @Test
    @Ignore("Progress dialog disappears too quickly because the REST data is too small.")
    public void pullDownToRefresh_AndVerifyProgressDialogIsShown() {
        onView(withId(R.id.contentView))
                .perform(swipeDown());
        onView(withText(R.string.dialog__loading__title))
                .check(matches(isDisplayed()));
        onView(withText(R.string.dialog__loading__message))
                .check(matches(isDisplayed()));
    }

    @Test
    public void verifyRecyclerViewIsPopulatedWithCorrectData() {
        onView(withId(R.id.posts_fragment__posts_recycler_view))
                .perform(scrollToPosition(FIRST_POSITION_ON_LIST));
        onView(withText(FIRST_TEXT_ON_LIST)).check(matches(isDisplayed()));
        onView(withId(R.id.posts_fragment__posts_recycler_view))
                .perform(scrollToPosition(MIDDLE_POSITION_ON_LIST));
        onView(withText(MIDDLE_TEXT_ON_LIST)).check(matches(isDisplayed()));
        onView(withId(R.id.posts_fragment__posts_recycler_view))
                .perform(scrollToPosition(LAST_POSITION_ON_LIST));
        onView(withText(LAST_TEXT_ON_LIST)).check(matches(isDisplayed()));
    }

    // ACTIVITY // **********************************************************************************************************

    @Test
    public void clickRecyclerViewItem_AndVerifyTheCorrectActivityIsLaunched() {
        onView(withId(R.id.posts_fragment__posts_recycler_view))
                .perform(actionOnItemAtPosition(FIRST_POSITION_ON_LIST, click()));
        intended(hasComponent(PostActivity.class.getName()));
    }

    @Test
    public void clickRecyclerViewItemAndThenHomeButton_AndVerifyTheCorrectActivityIsResumed() {
        onView(withId(R.id.posts_fragment__posts_recycler_view))
                .perform(actionOnItemAtPosition(FIRST_POSITION_ON_LIST, click()));
        intended(hasComponent(PostActivity.class.getName()));
        onView(withText(R.string.post_activity__label)).check(matches(isDisplayed()));
        onView(withContentDescription(ANDROID_R_ID_HOME_CONTENT_DESCRIPTION))
                .perform(click());
        onView(withText(FIRST_TEXT_ON_LIST)).check(matches(isDisplayed()));
    }

}
