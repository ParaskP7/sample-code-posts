package io.petros.posts;

import android.content.Intent;
import android.os.Bundle;
import android.support.test.rule.ActivityTestRule;

import io.petros.posts.model.Post;
import io.petros.posts.model.User;

public class GeneralEspressoTestHelper {

    protected static final String ANDROID_R_ID_HOME_CONTENT_DESCRIPTION = "Navigate up"; // Instead of: withId(android.R.id.home)

    // Model specific fields.
    protected User user = getTestUser();
    protected Post post = getTestPost();

    // ESPRESSO // **********************************************************************************************************

    protected void launchActivity(final ActivityTestRule activityTestRule, final Bundle extras) {
        final Intent intent = new Intent();
        intent.putExtras(extras);
        activityTestRule.launchActivity(intent);
    }

    // USER // **************************************************************************************************************

    private User getTestUser() {
        final User testUser = new User();
        testUser.setId(1);
        testUser.setName("Leanne Graham");
        testUser.setUsername("Bret");
        testUser.setEmail("Sincere@april.biz");
        return testUser;
    }

    // POST // **************************************************************************************************************

    private Post getTestPost() {
        final Post testPost = new Post();
        testPost.setUserId(1);
        testPost.setId(1);
        testPost.setTitle("sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
        testPost.setBody("quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae "
                + "ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto");
        return testPost;
    }

    protected Bundle getExtras(final Post post) {
        final Bundle extras = new Bundle();
        extras.putInt(Post.USER_ID, post.getUserId());
        extras.putInt(Post.ID, post.getId());
        extras.putString(Post.TITLE, post.getTitle());
        extras.putString(Post.BODY, post.getBody());
        return extras;
    }

}
