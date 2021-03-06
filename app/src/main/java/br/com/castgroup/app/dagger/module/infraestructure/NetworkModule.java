package br.com.castgroup.app.dagger.module.infraestructure;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Named;
import javax.inject.Singleton;

import br.com.castgroup.app.infraestructure.storage.service.GitHubOAuthService;
import br.com.castgroup.app.infraestructure.storage.service.GitHubService;
import br.com.castgroup.app.infraestructure.storage.service.GitHubStatusService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Module for network instances (Retrofit configurations).
 * <p>
 * Created by falvojr on 1/12/17.
 */
@Module
public class NetworkModule {

    //TODO (07) Dagger: Modulo que configura as instancias do Retrofit (uma para cada API)
    //Importante: @Provides, @Singleton, @Named e RxJava2CallAdapterFactory
    //Link Util: https://gist.github.com/falvojr/8ed285296a53052086d9a346018845ce

    static final String RETROFIT_GITHUB = "GitHub";
    static final String RETROFIT_GITHUB_STATUS = "GitHubStatus";
    static final String RETROFIT_GITHUB_OAUTH = "GitHubOAuth";

    @Provides
    @Singleton
    Gson providesGson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                .create();
    }

    @Provides
    @Singleton
    GsonConverterFactory providesGsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @Singleton
    RxJava2CallAdapterFactory providesRxJavaCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    @Named(RETROFIT_GITHUB)
    Retrofit providesRetrofitGitHub(GsonConverterFactory gsonFactory,
                                    RxJava2CallAdapterFactory rxFactory) {
        return buildRetrofit(gsonFactory, rxFactory, GitHubService.BASE_URL);
    }

    @Provides
    @Singleton
    @Named(RETROFIT_GITHUB_STATUS)
    Retrofit providesRetrofitGitHubStatus(GsonConverterFactory gsonFactory,
                                          RxJava2CallAdapterFactory rxFactory) {
        return buildRetrofit(gsonFactory, rxFactory, GitHubStatusService.BASE_URL);
    }

    @Provides
    @Singleton
    @Named(RETROFIT_GITHUB_OAUTH)
    Retrofit providesRetrofitGitHubOAuth(GsonConverterFactory gsonFactory,
                                         RxJava2CallAdapterFactory rxFactory) {
        return buildRetrofit(gsonFactory, rxFactory, GitHubOAuthService.BASE_URL);
    }

    private Retrofit buildRetrofit(GsonConverterFactory converterFactory, RxJava2CallAdapterFactory callAdapterFactory, String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .build();
    }
}
