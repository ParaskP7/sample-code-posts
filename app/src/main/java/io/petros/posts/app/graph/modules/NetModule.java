package io.petros.posts.app.graph.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.petros.posts.service.retrofit.RetrofitService;
import io.petros.posts.util.rx.RxSchedulers;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetModule {

    private final String baseUrl;

    public NetModule(final String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    public static RxSchedulers providesRxSchedulers() {
        return new RxSchedulers(Schedulers.io(), Schedulers.computation(), Schedulers.trampoline(),
                AndroidSchedulers.mainThread());
    }

    @Provides
    @Singleton
    RetrofitService provideRetroService() {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        return retrofit.create(RetrofitService.class);
    }

}
