package com.threekites.thessescape.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.threekites.thessescape.Models.User;
import com.threekites.thessescape.R;

public class HomeActivity extends AppCompatActivity {

    private TextView userNameTv;
    private User mUser;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();

        if (null != getIntent().getExtras()) {
            mUser = new Gson().fromJson(getIntent().getStringExtra("user"), User.class);
            userNameTv.setText("Welcome to ThessEscape " + mUser.getUserName());
        }

    }

    private void initViews() {
        userNameTv = findViewById(R.id.userNameTv);
    }

}
