package io.petros.posts.app.graph.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.petros.posts.app.PostsApplication;
import io.petros.posts.app.actions.AppSnackbarActions;

@Module
public class AppModule {

    private final PostsApplication application;

    public AppModule(final PostsApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    PostsApplication providesPostsApplication() {
        return application;
    }

    @Provides
    @Singleton
    AppSnackbarActions providesAppSnackbarActions() {
        return new AppSnackbarActions(application);
    }

}
