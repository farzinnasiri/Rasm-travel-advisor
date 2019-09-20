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
import com.rasm.Administering.User;

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


        username.setText(Administer.getInstance().getUsername());


        User user = Administer.getInstance().getUser(Administer.getInstance().getUsername());

        avatar.setImageResource(user.getImage());
        score.setText(user.getScore());
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        // inja bayad data haye jadid save shan

    }
}
