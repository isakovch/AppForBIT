package com.chyngyz.testapp.ui.list;

import com.chyngyz.testapp.data.entity.Car;
import com.chyngyz.testapp.data.entity.User;
import com.chyngyz.testapp.ui.ILifecycle;
import com.chyngyz.testapp.ui.IProgressBar;

import java.util.ArrayList;

public interface DataListContract {

    interface View extends IProgressBar {
        void showUsersList(User user, Car car);

        void showError(String error);
    }

    interface Presenter extends ILifecycle<View> {
        void fetchUserInfo();
    }
}
