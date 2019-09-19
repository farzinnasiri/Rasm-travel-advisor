package com.rasm;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.rasm.Administering.Administer;

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
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
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
        String username = this.username.getText().toString();
        String password = this.password.getText().toString();
        String phone = phoneNumber.getText().toString() ;

            if(if_obey_rules()){

                makeToast("اطلاعات شما با موفقیت ثبت گردید");
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
//                intent.putExtra("username", username);
                startActivity(intent);
            }


    }

    private boolean if_obey_rules() {
        String username = this.username.getText().toString();
        String password = this.password.getText().toString();
        String phone = phoneNumber.getText().toString() ;
        if(Administer.getInstance().if_username_exist(username)){
            makeToast( "این نام کاربری توسط کاربر دیگری ساخته شده است" );

        }
        else if(!Pattern.matches("(\\+98|0)?9\\d{9}" , phone)){
            makeToast( "شماره تلفن معتبر نمی باشد"  );
            return false;
        } else if( password.length() < 4) {
            makeToast("رمز عبور باید از 4 کارکتر بیشتر باشد");
            return false;
        }
            return true;
    }


    private void makeToast(String text) {
        Toast.makeText(SignUpActivity.this, text, Toast.LENGTH_SHORT).show();

    }


}
