package com.threekites.thessescape.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.threekites.thessescape.Managers.Manager;
import com.threekites.thessescape.Models.User;
import com.threekites.thessescape.R;
import com.threekites.thessescape.Utils.Dialogs;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class SignUpActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText, usernameEditText;
    private Button signUpButton;
    private SweetAlertDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initViews();
        setUpListeners();
    }

    private void initViews() {
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        signUpButton = findViewById(R.id.signUpButton);
        usernameEditText = findViewById(R.id.usernameEditText);
    }

    private void setUpListeners() {
        signUpButton.setOnClickListener(v -> {
            if (emailEditText.getText().toString().trim().isEmpty() || passwordEditText.getText().toString().trim().isEmpty() || usernameEditText.getText().toString().trim().isEmpty())  {
                Toast.makeText(SignUpActivity.this, "Please fill all required fields.", Toast.LENGTH_SHORT).show();
            } else {
                loading = Dialogs.loadingDialog(SignUpActivity.this);
                loading.show();
                User user = new User();
                user.setEmail(emailEditText.getText().toString().trim());
                user.setUserName(usernameEditText.getText().toString().trim());
                user.setPassword(passwordEditText.getText().toString().trim());
                new Manager().signUp(SignUpActivity.this, user);
            }
        });
    }

    public void onCreateUserCallBack(boolean success) {
        dismissDialog();
        if (success) {
            Dialogs.signUpSuccessfullyDialog(this).setConfirmClickListener(sweetAlertDialog -> {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }).show();
        } else {
            Dialogs.signUpErrorDialog(this);
        }
    }

    private void dismissDialog() {
        if (null != loading && loading.isShowing()) {
            loading.dismiss();
        }
    }

}
