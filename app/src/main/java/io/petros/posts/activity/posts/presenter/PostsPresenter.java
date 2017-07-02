package io.petros.posts.activity.posts.presenter;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;

import io.petros.posts.activity.posts.view.PostsView;

public interface PostsPresenter extends MvpPresenter<PostsView> {

    void loadPosts(final boolean pullToRefresh);

}
