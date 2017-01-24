package io.petros.posts.activity.post.view;

public interface PostView {

    void loadPostUserAndPost();

    void loadPostUser(final String userEmail, final String userName, final String userUsername);

    void notifyUserAboutUserUnavailability();

    void notifyUserAboutInternetUnavailability();

    void loadPost(final String postTitle, final String postBody, final String numberOfComments);

    void notifyUserAboutPostUnavailability();

    void showError();

}
