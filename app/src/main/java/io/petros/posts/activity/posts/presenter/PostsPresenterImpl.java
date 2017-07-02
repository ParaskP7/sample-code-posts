package io.petros.posts.activity.posts.presenter;

import com.hannesdorfmann.mosby3.mvp.MvpNullObjectBasePresenter;

import java.util.List;

import javax.annotation.Nullable;

import io.petros.posts.activity.posts.model.PostsModel;
import io.petros.posts.activity.posts.view.PostsView;
import io.petros.posts.model.Post;
import io.petros.posts.model.User;
import io.petros.posts.service.detector.InternetAvailabilityDetector;
import io.petros.posts.service.retrofit.RetrofitService;
import io.petros.posts.util.rx.RxSchedulers;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class PostsPresenterImpl extends MvpNullObjectBasePresenter<PostsView> implements PostsPresenter {

    private final PostsModel postsModel;
    private final RetrofitService retrofitService;
    private final RxSchedulers rxSchedulers;
    private final InternetAvailabilityDetector internetAvailabilityDetector;

    @Nullable private Disposable subscription;

    public PostsPresenterImpl(final PostsModel postsModel, final RetrofitService retrofitService, final RxSchedulers rxSchedulers,
                              final InternetAvailabilityDetector internetAvailabilityDetector) {
        this.postsModel = postsModel;
        this.retrofitService = retrofitService;
        this.rxSchedulers = rxSchedulers;
        this.internetAvailabilityDetector = internetAvailabilityDetector;
    }

    @Override
    public void loadPosts(final boolean pullToRefresh) {
        if (internetAvailabilityDetector.isAvailable()) {
            getView().showLoading(pullToRefresh);
            loadPosts();
        } else {
            getView().notifyUserAboutInternetUnavailability();
        }
    }

    // @formatter:off
    /**
     * Cannot do the below because of problems with Realm: java.lang.IllegalStateException: Realm access from incorrect thread.
     *
     * retrofitService.users()
     *         .subscribeOn(Schedulers.newThread())
     *         .flatMap(users -> {
     *             datastore.save().users(users);
     *             return retrofitService.posts();
     *         }).observeOn(AndroidSchedulers.mainThread())
     *         .subscribe(this::handlePostsResponse, this::handlePostsError);
     *
     * NOTE: Realm objects can only be accessed on the thread they were created.
     */
    // @formatter:on
    private void loadPosts() {
        Timber.i("Loading users...");
        subscription = retrofitService.users()
                .observeOn(rxSchedulers.getAndroidMainThreadScheduler())
                .subscribeOn(rxSchedulers.getIoScheduler())
                .subscribe(this::handleUsersResponse, this::handleUsersError);
    }

    private void handleUsersResponse(final List<User> users) {
        final boolean isSuccessful = postsModel.saveUsers(users);
        if (isSuccessful) {
            Timber.i("Loading posts...");
            subscription = retrofitService.posts()
                    .observeOn(rxSchedulers.getAndroidMainThreadScheduler())
                    .subscribeOn(rxSchedulers.getIoScheduler())
                    .subscribe(this::handlePostsResponse, this::handlePostsError);
        } else {
            getView().notifyUserAboutUsersUnavailability();
        }
    }

    private void handleUsersError(final Throwable throwable) {
        Timber.w(throwable, "Error while loading users...");
        getView().showError(throwable, false);
    }

    private void handlePostsResponse(final List<Post> posts) {
        getView().setData(posts);
        getView().showContent();
    }

    private void handlePostsError(final Throwable throwable) {
        Timber.w(throwable, "Error while loading posts...");
        getView().showError(throwable, false);
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (subscription != null) {
            subscription.dispose();
        }
    }

}
