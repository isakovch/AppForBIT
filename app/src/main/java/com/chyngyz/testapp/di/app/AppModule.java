package com.chyngyz.testapp.di.app;

import android.content.Context;

import com.chyngyz.testapp.data.resource.ResourceManager;
import com.chyngyz.testapp.data.storage.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
@Singleton
public class AppModule {

    private final Context mContext;

    public AppModule(Context context) {
        this.mContext = context;
    }

    @Singleton
    @Provides
    Context provideContext() {
        return mContext;
    }

    @Singleton
    @Provides
    ResourceManager provideResourceManager(Context context) {
        return new ResourceManager(context);
    }

    @Singleton
    @Provides
    PreferenceManager providePreferenceManager() {
        return new PreferenceManager(mContext);
    }

}