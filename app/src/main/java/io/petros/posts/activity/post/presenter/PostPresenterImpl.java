package io.petros.posts.activity.post.presenter;

import android.os.Bundle;

import java.util.List;

import javax.annotation.Nullable;

import io.petros.posts.activity.post.model.PostModel;
import io.petros.posts.activity.post.view.PostView;
import io.petros.posts.model.Comment;
import io.petros.posts.model.Post;
import io.petros.posts.model.User;
import io.petros.posts.service.detector.InternetAvailabilityDetector;
import io.petros.posts.service.retrofit.RetrofitService;
import io.petros.posts.util.rx.RxSchedulers;
import timber.log.Timber;

public class PostPresenterImpl implements PostPresenter {

    private final PostModel postModel;
    private final RetrofitService retrofitService;
    private final RxSchedulers rxSchedulers;
    private final InternetAvailabilityDetector internetAvailabilityDetector;

    @Nullable private PostView postView;

    public PostPresenterImpl(final PostModel postModel, final RetrofitService retrofitService, final RxSchedulers rxSchedulers,
                             final InternetAvailabilityDetector internetAvailabilityDetector) {
        this.postModel = postModel;
        this.retrofitService = retrofitService;
        this.rxSchedulers = rxSchedulers;
        this.internetAvailabilityDetector = internetAvailabilityDetector;
    }

    @Override
    public void attachView(final PostView postView) {
        this.postView = postView;
    }

    @Override
    public boolean isViewAttached() {
        return postView != null;
    }

    @Override
    @SuppressWarnings("PMD.AvoidThrowingNullPointerException")
    public PostView getView() {
        if (postView != null) {
            return postView;
        }
        throw new NullPointerException("PostView reference is null. Have you called attachView()?");
    }

    @Override
    public void loadPostUserAndPost(final Bundle extras) {
        loadPostUser(extras);
        loadPost(extras);
    }

    private void loadPostUser(final Bundle extras) {
        final Integer userId = extras.getInt(Post.USER_ID);
        @Nullable final User user = postModel.getUser(userId);
        if (user != null) {
            if (isViewAttached()) {
                getView().loadPostUser(user.getEmail(), user.getName(), user.getUsername());
            }
        } else {
            if (isViewAttached()) {
                getView().notifyUserAboutUserUnavailability();
            }
        }
    }

    private void loadPost(final Bundle extras) {
        if (internetAvailabilityDetector.isAvailable()) {
            @Nullable final String postTitle = extras.getString(Post.TITLE);
            @Nullable final String postBody = extras.getString(Post.BODY);
            final int postId = extras.getInt(Post.ID);
            if (postTitle != null && postBody != null && postId != 0) {
                loadPost(postTitle, postBody, postId);
            } else {
                if (isViewAttached()) {
                    getView().notifyUserAboutPostUnavailability();
                }
            }
        } else {
            if (isViewAttached()) {
                getView().notifyUserAboutInternetUnavailability();
            }
        }
    }

    private void loadPost(final String postTitle, final String postBody, final Integer postId) {
        retrofitService.comments(postId)
                .observeOn(rxSchedulers.getAndroidMainThreadScheduler())
                .subscribeOn(rxSchedulers.getIoScheduler())
                .subscribe((comments) -> handleResponse(postTitle, postBody, comments), this::handleError);
    }

    private void handleResponse(final String postTitle, final String postBody, final List<Comment> comments) {
        if (isViewAttached()) {
            getView().loadPost(postTitle, postBody, String.valueOf(comments.size()));
        }
    }

    private void handleError(final Throwable throwable) {
        Timber.e(throwable, "Error getting comments...");
        if (isViewAttached()) {
            getView().showError();
        }
    }

}
