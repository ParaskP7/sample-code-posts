package io.petros.posts.activity.posts.presenter;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import io.petros.posts.activity.posts.view.PostsView;

public interface PostsPresenter extends MvpPresenter<PostsView> {

    void loadPosts(final boolean pullToRefresh);

}
