package io.petros.posts.activity.post.presenter;

import android.os.Bundle;

import io.petros.posts.activity.post.view.PostView;

public interface PostPresenter {

    void attachView(final PostView postView);

    boolean isViewAttached();

    PostView getView();

    void loadPostUserAndPost(final Bundle extras);

    void detachView();

}
