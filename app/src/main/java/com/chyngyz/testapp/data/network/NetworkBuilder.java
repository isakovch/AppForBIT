package com.chyngyz.testapp.data.network;

import com.chyngyz.testapp.BuildConfig;

import io.reactivex.disposables.Disposable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkBuilder {
    private static RetrofitManager sService = null;

    public RetrofitManager initRetrofit(OkHttpClient okHttpClient) {
        if (sService == null) {
            sService = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
                    .create(RetrofitManager.class);
        }
        return sService;
    }
}
