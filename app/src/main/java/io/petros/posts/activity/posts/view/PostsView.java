package io.petros.posts.activity.posts.view;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.List;

import io.petros.posts.model.Post;

public interface PostsView extends MvpLceView<List<Post>> {

    void notifyUserAboutUsersUnavailability();

    void notifyUserAboutInternetUnavailability();

}
