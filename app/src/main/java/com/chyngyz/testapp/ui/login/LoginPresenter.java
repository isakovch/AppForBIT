package com.chyngyz.testapp.ui.login;

import android.os.Bundle;

import com.chyngyz.testapp.R;
import com.chyngyz.testapp.data.resource.ResourceManager;
import com.chyngyz.testapp.data.storage.PreferenceManager;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONException;

public class LoginPresenter implements LoginContract.Presenter {

    private PreferenceManager mPreferenceManager;
    private ResourceManager mResourceManager;

    private LoginContract.View mView;

    public LoginPresenter(PreferenceManager preferenceManager, ResourceManager resourceManager) {
        this.mPreferenceManager = preferenceManager;
        this.mResourceManager = resourceManager;
    }

    @Override
    public void bind(LoginContract.View view) {
        this.mView = view;
    }

    @Override
    public void unbind() {
        this.mView = null;
    }

    @Override
    public void registerFacebookCallback(CallbackManager callbackManager) {
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                (object, response) -> {
                                    try {
                                        saveUserData(
                                                object.getString("first_name"),
                                                object.getString("last_name"));

                                        mView.loginSuccess();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,first_name,last_name, email,gender,birthday");
                        request.setParameters(parameters);
                        request.executeAsync();
                    }

                    @Override
                    public void onCancel() {
                        mView.showError(mResourceManager.getStringResource(R.string.msg_connection_terminated));
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        mView.showError(exception.getMessage());
                    }
                });
    }

    @Override
    public void checkForAuthenticatedUser() {
        if (mPreferenceManager.isAuthenticated()) {
            mView.loginSuccess();
            return;
        }

        mView.registerCallback();
    }

    private void saveUserData(String firstName, String lastName) {
        mPreferenceManager.saveData(String.format("%1s %2s", firstName, lastName));
    }
}
