package io.petros.posts.app;

import android.app.Activity;
import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import javax.inject.Inject;

import io.petros.posts.app.actions.AppSnackbarActions;
import io.petros.posts.app.actions.SnackbarActions;
import io.petros.posts.app.graph.components.ApplicationComponent;
import io.petros.posts.app.graph.components.DaggerApplicationComponent;
import io.petros.posts.app.graph.modules.ActivityModule;
import io.petros.posts.app.graph.modules.AppModule;
import io.petros.posts.app.graph.modules.DataModule;
import io.petros.posts.app.graph.modules.NetModule;
import io.petros.posts.app.graph.modules.ServiceModule;
import io.realm.Realm;
import timber.log.Timber;

import static io.petros.posts.util.PostsUtilities.BASE_URL;

@SuppressWarnings("checkstyle:overloadmethodsdeclarationorder")
public class PostsApplication extends Application {

    private static ApplicationComponent applicationComponent;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true); // NOTE: This enables the "proxy" trick on the vector images.
    }

    @Inject AppSnackbarActions appSnackbarActions;

    // HELPER // ************************************************************************************************************

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public static SnackbarActions snackbar(final Activity activity) {
        return ((PostsApplication) activity.getApplication()).snackbar();
    }

    // APPLICATION // *******************************************************************************************************

    @Override
    public void onCreate() {
        super.onCreate();
        initDagger();
        initRealm();
        initTimber();
        Timber.i("Posts application created!");
    }

    private void initDagger() {
        applicationComponent = DaggerApplicationComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(BASE_URL))
                .dataModule(new DataModule())
                .serviceModule(new ServiceModule())
                .activityModule(new ActivityModule()).build();
        applicationComponent.inject(this);
    }

    private void initTimber() {
        Timber.plant(new Timber.DebugTree());
    }

    protected void initRealm() {
        Realm.init(this);
    }

    // GET // ***************************************************************************************************************

    public Realm getDefaultRealmInstance() {
        return Realm.getDefaultInstance();
    }

    // ACTIONS // ***********************************************************************************************************

    public SnackbarActions snackbar() {
        return appSnackbarActions;
    }

}
