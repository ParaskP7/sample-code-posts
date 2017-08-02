package io.petros.posts.activity.posts.view.recycler;

import android.content.ComponentName;
import android.content.Intent;
import android.view.View;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.Shadows;
import org.robolectric.shadows.ShadowActivity;

import io.petros.posts.PreconfiguredRobolectricTestRunner;
import io.petros.posts.RobolectricGeneralTestHelper;
import io.petros.posts.activity.post.view.PostActivity;
import io.petros.posts.activity.posts.PostsActivity;
import io.petros.posts.model.Post;

import static org.assertj.core.api.Assertions.assertThat;

@Ignore("Problem with RxJava: Attempted to consume batched input events but the input event receiver has already been disposed.")
@RunWith(PreconfiguredRobolectricTestRunner.class)
public class OnPostViewClickListenerTest extends RobolectricGeneralTestHelper {

    private PostsActivity postsActivity;
    private OnPostViewClickListener onPostViewClickListener;
    @Mock private View viewMock;

    @Before
    public void setUp() {
        setUpMocks();
        setUpOnClickListener();
    }

    private void setUpOnClickListener() {
        postsActivity = Robolectric.setupActivity(PostsActivity.class);
        onPostViewClickListener = new OnPostViewClickListener(postsActivity);
    }

    @Test
    public void onViewClickTest() {
        onPostViewClickListener.onViewClick(viewMock, post);

        final ShadowActivity shadowActivity = Shadows.shadowOf(postsActivity);
        final Intent intent = shadowActivity.peekNextStartedActivityForResult().intent;
        assertThat(intent.getComponent()).isEqualTo(new ComponentName(postsActivity, PostActivity.class));
        assertThat(intent.getExtras().getInt(Post.USER_ID)).isEqualTo(getExtras(post).getInt(Post.USER_ID));
        assertThat(intent.getExtras().getInt(Post.ID)).isEqualTo(getExtras(post).getInt(Post.ID));
        assertThat(intent.getExtras().getInt(Post.TITLE)).isEqualTo(getExtras(post).getInt(Post.TITLE));
        assertThat(intent.getExtras().getInt(Post.BODY)).isEqualTo(getExtras(post).getInt(Post.BODY));
    }

}
