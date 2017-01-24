package io.petros.posts.app.graph.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.petros.posts.app.PostsApplication;
import io.petros.posts.service.detector.InternetAvailabilityDetector;

@Module
public class ServiceModule {

    @Provides
    @Singleton
    InternetAvailabilityDetector provideInternetAvailabilityDetector(final PostsApplication application) {
        return new InternetAvailabilityDetector(application);
    }

}
