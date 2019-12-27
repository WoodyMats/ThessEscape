package com.threekites.thessescape;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button signUpButton;

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
    }

    private void setUpListeners() {
        signUpButton.setOnClickListener(v -> {
            if (emailEditText.getText().toString().isEmpty() || passwordEditText.getText().toString().isEmpty()) {
                Toast.makeText(SignUpActivity.this, "Please fill all required fields.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(SignUpActivity.this, "User Created.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
