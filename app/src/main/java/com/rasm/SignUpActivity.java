package com.rasm;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.regex.Pattern;


public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText username;
    private EditText password;
    private EditText phoneNumber;

    private Button signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_layout);

        initUi();

        signUp.setOnClickListener(this);

    }

    private void initUi(){

        username = findViewById(R.id.username_input);
        password = findViewById(R.id.signup_pass_input);
        signUp = findViewById(R.id.signupBtn);
        phoneNumber = findViewById(R.id.signup_phone_input);

    }

    @Override
    public void onClick(View v) {
        signUp();

    }


    public void signUp() {
        String email = username.getText().toString();
        String password = this.password.getText().toString();


            makeToast("اطلاعات شما با موفقیت ثبت گردید");
            Intent intent =new Intent(SignUpActivity.this, MainActivity.class);
            intent.putExtra("email",email);
            startActivity(intent);
            finish();


    }



    private void makeToast(String text) {
        Toast.makeText(SignUpActivity.this, text, Toast.LENGTH_SHORT).show();

    }


}
