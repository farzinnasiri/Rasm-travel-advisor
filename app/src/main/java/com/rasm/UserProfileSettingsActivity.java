package com.rasm;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.rasm.Administering.Administer;

import java.util.HashMap;

public class UserProfileSettingsActivity extends AppCompatActivity {

    private ImageView avatar;
    private TextView username;
    private TextView score;
    private EditText email;
    private EditText phone;
    private TextView logOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_settings);

        initUI();

    }

    private void initUI() {
        username = findViewById(R.id.dashboard_txt_username);
        avatar = findViewById(R.id.dashboard_user_photo);
        score = findViewById(R.id.dashboard_txt_points);
        email = findViewById(R.id.email_EditText);
        phone = findViewById(R.id.phone_EditText);
        logOut = findViewById(R.id.logout_txt_button);


        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Administer.getInstance().setLoggedIn(false , "");
                startActivity(new Intent(UserProfileSettingsActivity.this , LoginActivity.class));
            }
        });
//
        HashMap userData = Administer.getInstance().getUserDatas(username.getText().toString());



        username.setText(Administer.getInstance().getUsername());
        avatar.setImageBitmap((Bitmap) (userData.get("image")));
        score.setText((String)(userData.get("score")));
        if((String)(userData.get("email")) == null){
        email.setText("");

        }else {
            email.setText((String) (userData.get("email")));
        }
        phone.setText((String)(userData.get("phone")));
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        // inja bayad data haye jadid save shan

    }
}
