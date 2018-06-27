package com.chyngyz.testapp.data.network;

import com.chyngyz.testapp.config.RestConfig;
import com.chyngyz.testapp.data.entity.Car;
import com.chyngyz.testapp.data.entity.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitManager {

    @GET(RestConfig.COMMON_ENDPOINT)
    Call<User> getUsers(@Query("action") String action);

    @GET(RestConfig.COMMON_ENDPOINT)
    Call<Car> getCars(@Query("action") String action);

}
