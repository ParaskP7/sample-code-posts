package io.petros.posts.app.graph.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.petros.posts.activity.post.model.PostModel;
import io.petros.posts.activity.post.model.PostModelImpl;
import io.petros.posts.activity.post.presenter.PostPresenter;
import io.petros.posts.activity.post.presenter.PostPresenterImpl;
import io.petros.posts.activity.posts.model.PostsModel;
import io.petros.posts.activity.posts.model.PostsModelImpl;
import io.petros.posts.activity.posts.presenter.PostsPresenter;
import io.petros.posts.activity.posts.presenter.PostsPresenterImpl;
import io.petros.posts.datastore.Datastore;
import io.petros.posts.service.detector.InternetAvailabilityDetector;
import io.petros.posts.service.retrofit.RetrofitService;
import io.petros.posts.util.rx.RxSchedulers;

@Module
public class ActivityModule {

    @Provides
    @Singleton
    PostsModel providesPostsModel(final Datastore datastore) {
        return new PostsModelImpl(datastore);
    }

    @Provides
    @Singleton
    PostsPresenter providesPostsPresenter(final PostsModel postsModel, final RetrofitService retrofitService,
                                          final RxSchedulers rxSchedulers,
                                          final InternetAvailabilityDetector internetAvailabilityDetector) {
        return new PostsPresenterImpl(postsModel, retrofitService, rxSchedulers, internetAvailabilityDetector);
    }

    @Provides
    @Singleton
    PostModel providesPostModel(final Datastore datastore) {
        return new PostModelImpl(datastore);
    }

    @Provides
    @Singleton
    PostPresenter providesPostPresenter(final PostModel postModel, final RetrofitService retrofitService,
                                        final RxSchedulers rxSchedulers,
                                        final InternetAvailabilityDetector internetAvailabilityDetector) {
        return new PostPresenterImpl(postModel, retrofitService, rxSchedulers, internetAvailabilityDetector);
    }

}
