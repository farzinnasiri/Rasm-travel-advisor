package com.rasm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button loginBtn;
    private TextView signUpTextBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        initUi();

        loginBtn.setOnClickListener(this);
        signUpTextBtn.setOnClickListener(this);

    }

    private void initUi() {
        loginBtn = findViewById(R.id.loginBtn);
        signUpTextBtn = findViewById(R.id.signup_text_btn);

    }

    @Override
    public void onClick(View v) {
        if (v instanceof Button) {
            login();
        } else if (v instanceof TextView) {
            signUp();
        }


    }

    public void login() {

    }

    public void signUp() {
        switchActivity(SignUpActivity.class);

    }

    private void switchActivity(Class cls) {
        startActivity(new Intent(LoginActivity.this, cls));
        if (cls.equals(MainActivity.class)) {
            finish();

        }
    }
}

