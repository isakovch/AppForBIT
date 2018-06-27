package com.chyngyz.testapp.di.list;

import com.chyngyz.testapp.data.network.RetrofitManager;
import com.chyngyz.testapp.data.resource.ResourceManager;
import com.chyngyz.testapp.ui.list.DataListContract;
import com.chyngyz.testapp.ui.list.DataListPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ListModule {

    @Provides
    DataListContract.Presenter providePresenter(RetrofitManager retrofitManager, ResourceManager resourceManager) {
        return new DataListPresenter(retrofitManager, resourceManager);
    }

}
