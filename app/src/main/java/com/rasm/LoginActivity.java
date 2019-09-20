package com.rasm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rasm.Administering.Administer;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button loginBtn;
    private EditText user_or_phone_login;
    private EditText pass_login;
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
        user_or_phone_login = findViewById(R.id.username_phoneNumber_input);
        pass_login = findViewById(R.id.login_pass_input);


    }

    @Override
    public void onClick(View v) {
        if (v instanceof Button) {
            if(if_info_is_correct()) {
               Administer.getInstance().setLoggedIn(true , user_or_phone_login.getText().toString() );
                login();
            }
            else{
                Toast.makeText(this ,  "اطلاعات وارد شده صحیح نمی باشد" , Toast.LENGTH_SHORT).show();
            }
        } else if (v instanceof TextView) {
            signUp();
        }


    }

    private boolean if_info_is_correct() {
        return Administer.getInstance().check_info_of_user_is_correct(user_or_phone_login.getText().toString() , pass_login.getText().toString());
    }

    public void login() {
        switchActivity(MainActivity.class);
    }

    public void signUp() {
        switchActivity(SignUpActivity.class);

    }

    private void switchActivity(Class cls) {
        startActivity(new Intent(LoginActivity.this, cls));

    }
}

