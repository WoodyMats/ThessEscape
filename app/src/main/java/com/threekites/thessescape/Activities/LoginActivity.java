package com.threekites.thessescape.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.threekites.thessescape.Managers.Manager;
import com.threekites.thessescape.Models.User;
import com.threekites.thessescape.R;
import com.threekites.thessescape.Utils.Dialogs;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button signInButton, signUpButton;
    private SweetAlertDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        setUpListeners();
    }

    private void initViews() {
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        signInButton = findViewById(R.id.signInButton);
        signUpButton = findViewById(R.id.signUpButton);
    }

    private void setUpListeners() {
        signInButton.setOnClickListener(v -> {
            if (!emailEditText.getText().toString().trim().isEmpty() && !passwordEditText.getText().toString().trim().isEmpty()) {
                loading = Dialogs.loadingDialog(LoginActivity.this);
                loading.show();
                User user = new User();
                user.setEmail(emailEditText.getText().toString().trim());
                user.setPassword(passwordEditText.getText().toString().trim());
                new Manager().loginUser(LoginActivity.this, user);
            } else {
                Toast.makeText(LoginActivity.this, "Login Error! WRONG CREDENTIALS", Toast.LENGTH_SHORT).show();
            }
        });

        signUpButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        });
    }

    public void onUserLoginCallback(User user) {
        dismissDialog();
        if (null != user) {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            intent.putExtra("user", new Gson().toJson(user, User.class));
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(LoginActivity.this, "Login Error! WRONG CREDENTIALS", Toast.LENGTH_SHORT).show();
        }
    }

    private void dismissDialog() {
        if (null != loading && loading.isShowing()) {
            loading.dismiss();
        }
    }

}
