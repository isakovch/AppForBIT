package com.chyngyz.testapp.di.app;

import com.chyngyz.testapp.data.network.HttpClientBuilder;
import com.chyngyz.testapp.data.network.NetworkBuilder;
import com.chyngyz.testapp.data.network.RetrofitManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
@Singleton
public class NetworkModule {

    @Singleton
    @Provides
    RetrofitManager provideRetrofitApiService(OkHttpClient okHttpClient) {
        return new NetworkBuilder().initRetrofit(okHttpClient);
    }

    @Singleton
    @Provides
    OkHttpClient provideApolloOkHttpClient() {
        return new HttpClientBuilder().initOkHttpClient();
    }

}