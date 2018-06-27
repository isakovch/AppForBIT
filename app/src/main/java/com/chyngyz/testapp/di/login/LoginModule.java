package com.chyngyz.testapp.di.login;

import com.chyngyz.testapp.data.resource.ResourceManager;
import com.chyngyz.testapp.data.storage.PreferenceManager;
import com.chyngyz.testapp.ui.login.LoginContract;
import com.chyngyz.testapp.ui.login.LoginPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    @Provides
    LoginContract.Presenter providePresenter(PreferenceManager preferenceManager, ResourceManager resourceManager) {
        return new LoginPresenter(preferenceManager, resourceManager);
    }

}
