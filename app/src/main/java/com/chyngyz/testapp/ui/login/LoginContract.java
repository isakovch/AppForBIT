package com.chyngyz.testapp.ui.login;

import com.chyngyz.testapp.ui.ILifecycle;
import com.chyngyz.testapp.ui.IProgressBar;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.login.LoginResult;

public interface LoginContract {
    interface View {

        void loginSuccess();

        void showError(String error);

        void registerCallback();
    }

    interface Presenter extends ILifecycle<View> {
        void registerFacebookCallback(CallbackManager callbackManager);

        void checkForAuthenticatedUser();
    }
}
