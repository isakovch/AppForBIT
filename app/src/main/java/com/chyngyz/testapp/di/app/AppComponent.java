package com.chyngyz.testapp.di.app;

import com.chyngyz.testapp.di.login.LoginComponent;
import com.chyngyz.testapp.di.login.LoginModule;
import com.chyngyz.testapp.di.list.ListComponent;
import com.chyngyz.testapp.di.list.ListModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {

    LoginComponent include(LoginModule module);

    ListComponent include(ListModule module);

}
