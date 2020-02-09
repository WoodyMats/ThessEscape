package com.threekites.thessescape.Managers;

import android.content.Context;

import com.google.gson.JsonObject;
import com.threekites.thessescape.Activities.LoginActivity;
import com.threekites.thessescape.Activities.SignUpActivity;
import com.threekites.thessescape.Models.User;
import com.threekites.thessescape.Utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Manager {

    public void loginUser(Context context, User user) {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.MAIN_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CallsInterface callsInterface = retrofit.create(CallsInterface.class);
        Call<User> call = callsInterface.userLogin(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                int code = response.code();

                if (code == 200) {
                    ((LoginActivity) context).onUserLoginCallback(response.body());
                } else {
                    ((LoginActivity) context).onUserLoginCallback(null);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                ((LoginActivity) context).onUserLoginCallback(null);
            }
        });
    }

    public void signUp(Context context, User user) {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.MAIN_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CallsInterface callsInterface = retrofit.create(CallsInterface.class);
        Call<JsonObject> call = callsInterface.signUp(user);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                int code = response.code();

                if (code == 201) {
                    ((SignUpActivity) context).onCreateUserCallBack(true);
                } else {
                    ((SignUpActivity) context).onCreateUserCallBack(false);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ((SignUpActivity) context).onCreateUserCallBack(false);
            }
        });
    }

}
