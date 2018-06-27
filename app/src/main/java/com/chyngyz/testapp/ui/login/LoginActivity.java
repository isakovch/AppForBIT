package com.chyngyz.testapp.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chyngyz.testapp.R;
import com.chyngyz.testapp.TestApplication;
import com.chyngyz.testapp.di.login.LoginModule;
import com.chyngyz.testapp.ui.BaseActivity;
import com.chyngyz.testapp.ui.main.MainActivity;
import com.chyngyz.testapp.utils.MsgUtils;
import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;

import java.util.Collections;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginContract.View, View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private CallbackManager mCallbackManager;

    @Inject
    LoginContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initDependency();
        initPresenter();
        initToolbar();
    }

    private void initDependency() {
        TestApplication
                .getComponent()
                .include(new LoginModule())
                .inject(this);
    }

    private void initPresenter() {
        mPresenter.bind(this);
        mPresenter.checkForAuthenticatedUser();
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
    }

    @Override
    public void registerCallback() {
        mPresenter.registerFacebookCallback(mCallbackManager = CallbackManager.Factory.create());
    }

    @OnClick({R.id.btn_skip, R.id.btn_facebook})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_skip:
                switchMainActivity();
                break;
            case R.id.btn_facebook:
                connectToFacebook();
                break;
        }
    }

    protected void connectToFacebook() {
        LoginManager
                .getInstance()
                .logInWithReadPermissions(this, Collections.singletonList("public_profile"));
    }

    @Override
    public void loginSuccess() {
        switchMainActivity();
    }

    @Override
    public void showError(String error) {
        MsgUtils.showLongToast(this, error);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void switchMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTitle(R.string.title_login);
    }
}
