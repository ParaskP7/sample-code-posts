package io.petros.posts.app.graph.components;

import javax.inject.Singleton;

import dagger.Component;
import io.petros.posts.activity.post.view.PostActivity;
import io.petros.posts.activity.posts.PostsActivity;
import io.petros.posts.activity.posts.view.PostsFragment;
import io.petros.posts.activity.posts.view.recycler.PostViewHolder;
import io.petros.posts.app.PostsApplication;
import io.petros.posts.app.graph.modules.ActivityModule;
import io.petros.posts.app.graph.modules.AppModule;
import io.petros.posts.app.graph.modules.DataModule;
import io.petros.posts.app.graph.modules.NetModule;
import io.petros.posts.app.graph.modules.ServiceModule;

@Singleton
@Component(modules = {AppModule.class, NetModule.class, DataModule.class, ServiceModule.class, ActivityModule.class})
public interface ApplicationComponent {

    // APPLICATION // *******************************************************************************************************

    void inject(final PostsApplication application);

    // POSTS ACTIVITY // ****************************************************************************************************

    void inject(final PostsActivity postsActivity);

    void inject(final PostsFragment postsFragment);

    void inject(final PostViewHolder postViewHolder);

    // POST ACTIVITY // *****************************************************************************************************

    void inject(final PostActivity postActivity);

}
