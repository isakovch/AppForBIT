package com.chyngyz.testapp.data.network;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class HttpClientBuilder {

    private static final String TAG = "TEST_APP";
    private static final long TIMEOUT_INTERVAL = 20;

    public OkHttpClient initOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(message -> Log.d(TAG, "OKHTTP => " + message));
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .writeTimeout(TIMEOUT_INTERVAL, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_INTERVAL, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT_INTERVAL, TimeUnit.SECONDS)
                .build();
    }

}
