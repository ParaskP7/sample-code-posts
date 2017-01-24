package io.petros.posts.activity.post;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.petros.posts.GeneralEspressoTestHelper;
import io.petros.posts.R;
import io.petros.posts.activity.post.view.PostActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class PostActivityEspressoTest extends GeneralEspressoTestHelper {

    private static final boolean INITIAL_TOUCH_MODE = false;
    private static final boolean LAUNCH_ACTIVITY = false;

    private static final int NUMBER_OF_COMMENTS_ON_FIRST_POST = 5;

    @Rule
    @SuppressFBWarnings("URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD")
    public ActivityTestRule<PostActivity> activityTestRule =
            new ActivityTestRule<>(PostActivity.class, INITIAL_TOUCH_MODE, LAUNCH_ACTIVITY);

    // VIEW // **************************************************************************************************************

    @Test
    public void verifyActivityTitleIsShown() {
        launchActivity(activityTestRule, getExtras(post));
        onView(withText(R.string.post_activity__label))
                .check(matches(isDisplayed()));
    }

    @Test
    public void verifyActivityUserValuesIsShown() {
        launchActivity(activityTestRule, getExtras(post));
        onView(withId(R.id.activity_post__avatar_circle_image_view))
                .check(matches(isDisplayed()));
        onView(withId(R.id.activity_post__name_text_view))
                .check(matches(withText(user.getName())));
        onView(withId(R.id.activity_post__username_text_view))
                .check(matches(withText(user.getUsername())));
    }

    @Test
    public void verifyActivityPostValuesIsShown() {
        launchActivity(activityTestRule, getExtras(post));
        onView(withText(R.string.post_activity__title_label))
                .check(matches(isDisplayed()));
        onView(withId(R.id.activity_post__title_text_view))
                .check(matches(withText(post.getTitle())));
        onView(withText(R.string.post_activity__body_label))
                .check(matches(isDisplayed()));
        onView(withId(R.id.activity_post__body_text_view))
                .check(matches(withText(post.getBody())));
        onView(withText(R.string.post_activity__comments_label))
                .check(matches(isDisplayed()));
        onView(withId(R.id.activity_post__number_of_comments_text_view))
                .check(matches(withText(String.valueOf(NUMBER_OF_COMMENTS_ON_FIRST_POST))));
    }

    // ACTIVITY // **********************************************************************************************************

    @Test
    public void clickHomeButton_AndVerifyTheCorrectActivityIsLaunched() {
        launchActivity(activityTestRule, getExtras(post));
        onView(withContentDescription(ANDROID_R_ID_HOME_CONTENT_DESCRIPTION))
                .perform(click());
        assertThat(activityTestRule.getActivity().isFinishing()).isTrue();
    }

}
