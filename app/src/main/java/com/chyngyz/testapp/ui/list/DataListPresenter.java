package com.chyngyz.testapp.ui.list;

import android.support.annotation.NonNull;

import com.chyngyz.testapp.R;
import com.chyngyz.testapp.data.entity.Car;
import com.chyngyz.testapp.data.entity.User;
import com.chyngyz.testapp.data.network.RetrofitManager;
import com.chyngyz.testapp.data.resource.ResourceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataListPresenter implements DataListContract.Presenter {

    private RetrofitManager mRetrofitManager;
    private ResourceManager mResourceManager;

    private DataListContract.View mView;

    public DataListPresenter(RetrofitManager retrofitManager, ResourceManager resourceManager) {
        this.mRetrofitManager = retrofitManager;
        this.mResourceManager = resourceManager;
    }

    @Override
    public void bind(DataListContract.View view) {
        this.mView = view;
    }

    @Override
    public void unbind() {
        this.mView = null;
    }

    @Override
    public void fetchUserInfo() {
        mView.showLoadingIndicator();
        getUsers();
    }

    private void getUsers() {
        mRetrofitManager
                .getUsers("getUsers")
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            getCars(response.body());
                        } else {
                            mView.showError(mResourceManager.getStringResource(R.string.msg_connection_failure));
                            mView.hideLoadingIndicator();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                        mView.showError(t.getMessage());
                        mView.hideLoadingIndicator();
                    }
                });
    }

    private void getCars(User user) {
        mRetrofitManager
                .getCars("getCars")
                .enqueue(new Callback<Car>() {
                    @Override
                    public void onResponse(@NonNull Call<Car> call, @NonNull Response<Car> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            mView.showUsersList(user, response.body());
                        } else {
                            mView.showError(mResourceManager.getStringResource(R.string.msg_connection_failure));
                        }
                        mView.hideLoadingIndicator();
                    }

                    @Override
                    public void onFailure(@NonNull Call<Car> call, @NonNull Throwable t) {
                        mView.showError(t.getMessage());
                        mView.hideLoadingIndicator();
                    }
                });
    }
}
