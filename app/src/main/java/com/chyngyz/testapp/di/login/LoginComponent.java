package com.chyngyz.testapp.di.login;

import com.chyngyz.testapp.ui.login.LoginActivity;

import dagger.Subcomponent;

@Subcomponent(modules = LoginModule.class)
@LoginScope
public interface LoginComponent {

    void inject(LoginActivity activity);

}
