package com.chyngyz.testapp;

import android.app.Application;

import com.chyngyz.testapp.di.app.AppComponent;
import com.chyngyz.testapp.di.app.AppModule;
import com.chyngyz.testapp.di.app.DaggerAppComponent;
import com.chyngyz.testapp.di.app.NetworkModule;

public class TestApplication extends Application {

    private static AppComponent sComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initDependency();
    }

    private void initDependency() {
        sComponent = DaggerAppComponent
                .builder()
                .networkModule(new NetworkModule())
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getComponent() {
        return sComponent;
    }
}
