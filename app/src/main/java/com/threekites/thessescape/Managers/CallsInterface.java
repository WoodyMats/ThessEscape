package com.threekites.thessescape.Managers;

import com.google.gson.JsonObject;
import com.threekites.thessescape.Models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface CallsInterface {

    @Headers("Content-Type: application/json")
    @POST("users/signup")
    Call<JsonObject> signUp(@Body User user);

    @Headers("Content-Type: application/json")
    @POST("users/login")
    Call<User> userLogin(@Body User user);

}
